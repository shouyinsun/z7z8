package z7z8.byteCodeEnhance.attath;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * instrument 是 JVM 提供的一个可以修改已加载类的类库,专门为 Java 语言编写的插桩服务提供支持
 * 它需要依赖 JVMTI 的 Attach API 机制实现
 *
 * JVM TI（JVM TOOL INTERFACE，JVM 工具接口）是 JVM 提供的一套对 JVM进行操作的工具接口
 * 
 * @description: 自定义 class 转换器
 * @author: cash
 * @create: 2020/7/3 17:32
 **/
public class CustomizedTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("z7z8.byteCodeEnhance.attach.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
