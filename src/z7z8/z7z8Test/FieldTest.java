package z7z8.z7z8Test;

import com.sun.istack.internal.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * Field test
 * author cash
 * create 2018-10-21-20:15
 **/
public class FieldTest {

    private int mInt; // 普通成员变量

    public static String mStr; // 静态成员变量

    @Nullable
    private Object mObject; // 使用了注解的成员变量

    private List<String> mList; // 泛型字段

    public static void main(String[] args) throws Exception {
        FieldTest obj = new FieldTest();
        Class<? extends FieldTest> clazz = obj.getClass();

		/*
         * 普通属性字段的设置和获取
		 */
        Field normalField = clazz.getDeclaredField("mInt");
        setAccessible(normalField);
        String filedName = normalField.getName();//mInt
        System.out.println(filedName);
        normalField.set(obj, 100); // 设值
        int mIntValue = normalField.getInt(obj);// 取值 100;
        System.out.println(mIntValue);

		/*
		 * 静态属性字段的设值和获取 (obj传null)
		 */
        Field staticField = clazz.getDeclaredField("mStr");
        setAccessible(staticField);
        staticField.set(null, "static value");
        Object value = staticField.get(null);// static value
        System.out.println(value);

		/*
		 * 字段的枚举相关操作 (更多相关信息参照 我写的 Java注解)
		 */
        Field annotationField = clazz.getDeclaredField("mObject");
        setAccessible(annotationField);
        Annotation[] declaredAnnotations = annotationField
                .getDeclaredAnnotations();
        System.out.println(Arrays.toString(declaredAnnotations));

		/*
		 * 泛型字段
		 */
        Field genericField = clazz.getDeclaredField("mList");
        setAccessible(genericField);
        Type genericType = genericField.getGenericType();// java.util.List<java.lang.String>
        Class type = genericField.getType(); // interface java.util.List
        System.out.println(type);
        getActualType(genericType); //Class类型: class java.lang.String

    }

    // 私有的变量,需要设置为可访问
    private static void setAccessible(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
    }

    // 获取List中泛型的实际类型
    private static void getActualType(Type genericType) {
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type actualType = parameterizedType.getActualTypeArguments()[0];
            if (actualType instanceof TypeVariable) {// 泛型类型,比如T
                TypeVariable typeVariable = (TypeVariable) actualType;
                System.out.println("TypeVariable类型: " + typeVariable);

            } else if (actualType instanceof WildcardType) {// 含通配符? 类型
                WildcardType wildcardType = (WildcardType) actualType;
                System.out.println("WildcardType类型: " + wildcardType);

            } else if (actualType instanceof Class) { // 普通类对象
                Class cls = (Class) actualType;
                System.out.println("Class类型: " + cls); // class
                // java.lang.String
            }
        }

    }
}
