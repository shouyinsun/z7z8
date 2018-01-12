package z7z8.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author xuc
 * @time 2017年2月27日 下午8:33:56
 * @description
 * 通过脚本函数或者方法可以很方便的实现java接口，而不是在Java中调用
 * 通过脚本实现 java.lang.Runnable接口
 */
public class RunnableImpl {
	public static void main(String[] args) throws Exception {   
        ScriptEngineManager manager = new ScriptEngineManager();   
        ScriptEngine engine = manager.getEngineByName("JavaScript");   

        // JavaScript code in a String   
        String script = "function run() { println('run called'); }";   

        // evaluate script   
        engine.eval(script);   

        Invocable inv = (Invocable) engine;   

        // get Runnable interface object from engine. This interface methods   
        // are implemented by script functions with the matching name.   
        Runnable r = inv.getInterface(Runnable.class);   

        // start a new thread that runs the script implemented   
        // runnable interface   
        Thread th = new Thread(r);   
        th.start();   
    }   
}
