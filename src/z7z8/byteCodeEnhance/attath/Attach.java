package z7z8.byteCodeEnhance.attath;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @description: attach 到 指定pid的jvm,通过agent,动态修改已加载的class,做动态字节码增强
 * @author: cash
 * @create: 2020/7/3 18:02
 **/
public class Attach {
    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("39333");
        //load 自定义的agent
        vm.loadAgent("/xx/xxx/xxxx/agent.jar");
    }
}
