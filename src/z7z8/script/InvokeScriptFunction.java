package z7z8.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author xuc
 * @time 2017年2月27日 下午8:28:43
 * @description 调用脚本函数
 */
public class InvokeScriptFunction {
	 public static void main(String[] args) throws Exception {   
	        ScriptEngineManager manager = new ScriptEngineManager();   
	        ScriptEngine engine = manager.getEngineByName("JavaScript");   

	        // JavaScript code in a String   
	        String script = "function hello(name) { print('Hello, ' + name); }";   
	        // evaluate script   
	        engine.eval(script);   

	        // javax.script.Invocable is an optional interface.   
	        // Check whether your script engine implements or not!   
	        // Note that the JavaScript engine implements Invocable interface.   
	        Invocable inv = (Invocable) engine;   

	        // invoke the global function named "hello"   
	        inv.invokeFunction("hello", "Scripting!!" );   
	    }   
}
