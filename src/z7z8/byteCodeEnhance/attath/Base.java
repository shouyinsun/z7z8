package z7z8.byteCodeEnhance.attath;

import java.lang.management.ManagementFactory;

/**
 * @description:
 * @author: cash
 * @create: 2020/7/3 17:29
 **/
public class Base {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String s = name.split("@")[0];
        // 打印当前 Pid
        System.out.println("pid:"+s);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }
    public static void process() {
        System.out.println("process");
    }
}
