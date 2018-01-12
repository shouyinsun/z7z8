package z7z8.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author xuc
 * @time 2017年2月27日 下午8:14:52
 * @description  脚本引擎
 */
public class EvalScript {   
    public static void main(String[] args) throws Exception {   
        // create a script engine manager   
        ScriptEngineManager factory = new ScriptEngineManager();   
        // create a JavaScript engine   
        ScriptEngine engine = factory.getEngineByName("JavaScript");   
        // evaluate JavaScript code from String   
        engine.eval("print('Hello, World')");   
    }   
}
