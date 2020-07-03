package z7z8.byteCodeEnhance.javassist;

import javassist.*;

/**
 * CtClass（compile-time class）：编译时类信息,它是一个 class 文件在代码中的抽象表现形式,可以通过一个类的全限定名来获取一个 CtClass 对象,用来表示这个类文件。
 * ClassPool：是 一 张 保 存 CtClass 信 息 的HashTable,key 为类名,value 为类名对应的 CtClass 对象。当我们需要
 * CtMethod、CtField 对应的是类中的方法和属性
 * 
 * @description:
 * @author: cash
 * @create: 2020/7/3 16:53
 **/
public class Test {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
//        Base base =new Base();
//        base.process();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("z7z8.byteCodeEnhance.javassist.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        Base h = (Base)c.newInstance();
        h.process();

        Base base =new Base();
        base.process();
    }
}
