package z7z8.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 
 * @author cash
 * @date 2017年5月24日 下午12:31:52
 * @decription 泛型擦除
 * 泛型只在编译时存在  编译器生成字节码时,会进行泛型擦除 全部是 Object
 */
public class GenericErased {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1.getName());
        System.out.println(c2.getName());
        System.out.println(c1 == c2); // true
        
      
        //经过类型擦除,所有的泛型类实例都关联到同一份字节码上,泛型类的所有静态变量是共享的
        GT<Integer> gti = new GT<Integer>();
        gti.var=1;
        GT<String> gts = new GT<String>();
        gts.var=2;
        System.out.println(gti.var);
        
        
        System.out.println("----------------------------------");
         
        ArrayList<Integer> array=new ArrayList<Integer>();    
        array.add(1);//这样add方法只能存储整形,因为泛型类型的实例为Integer  
        //通过反射添加String 类型成员
        array.getClass().getMethod("add", Object.class).invoke(array, "asd");    
        for (int i=0;i<array.size();i++) {    
            System.out.println(array.get(i));    
        }    
        
    }

}

class GT<T>{
    public static int var=0;
    public void nothing(T x){}
}
