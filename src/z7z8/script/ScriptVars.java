package z7z8.script;

import java.io.File;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author xuc
 * @time 2017年2月27日 下午8:27:15
 * @description 脚本变量
 */
public class ScriptVars {
	public static void main(String[] args) throws Exception {   
        ScriptEngineManager manager = new ScriptEngineManager();   
        ScriptEngine engine = manager.getEngineByName("JavaScript");   

        File f = new File("test.txt");   
        // expose File object as variable to script   
        engine.put("file", f);   

        // evaluate a script string. The script accesses "file"    
        // variable and calls method on it   
        engine.eval("print(file.getAbsolutePath())");   
    }   
}
