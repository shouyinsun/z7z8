package z7z8.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author xuc
 * @time 2017年2月27日 下午8:17:32
 * @description 执行一个脚本文件
 */
public class EvalFile {   
    public static void main(String[] args) throws Exception {   
        // create a script engine manager   
        ScriptEngineManager factory = new ScriptEngineManager();   
        // create JavaScript engine   
        ScriptEngine engine = factory.getEngineByName("JavaScript");   
        // evaluate JavaScript code from given file - specified by first argument   
        engine.eval(new java.io.FileReader(args[0]));   
    }   
}