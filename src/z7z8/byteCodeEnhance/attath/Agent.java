package z7z8.byteCodeEnhance.attath;

import java.lang.instrument.Instrumentation;

/**
 * 定义一个Agent,借助Agent的能力将 Instrument 注入到要attach的 JVM 中
 *
 * @description:
 * @author: cash
 * @create: 2020/7/3 17:36
 **/
public class Agent {

    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的 Transformer，在其中利用 Javassist 做字节码替换
        inst.addTransformer(new CustomizedTransformer(), true);
        try {
            // 重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
