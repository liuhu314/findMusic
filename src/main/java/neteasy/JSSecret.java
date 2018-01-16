package neteasy;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class JSSecret {
    private static Invocable inv;
    public static final String encText = "encText";
    public static final String encSecKey = "encSecKey";

    /**
     * 从本地加载修改后的 js 文件到 scriptEngine
     */
//    public static void init()
//    {
//        try {
//            Path path = Paths.get("core.js", new String[0]);
//            byte[] bytes = Files.readAllBytes(path);
//            String js = new String(bytes);
//            ScriptEngineManager factory = new ScriptEngineManager();
//            ScriptEngine engine = factory.getEngineByName("JavaScript");
//            engine.eval(js);
//            inv = (Invocable)engine;
//            System.out.println("Init completed");
//        } catch (Exception var5) {
//            var5.printStackTrace();
//        }
//
//    }
    static {
        try {
            Path path = Paths.get("core.js");
            byte[] bytes = Files.readAllBytes(path);
            String js = new String(bytes);
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("JavaScript");
            engine.eval(js);
            inv = (Invocable) engine;
            System.out.println("Init completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ScriptObjectMirror get_params(String paras) throws Exception {
        ScriptObjectMirror so = (ScriptObjectMirror) inv.invokeFunction("myFunc", paras);
        return so;
    }

    public static HashMap<String, String> getDatas(String paras) {
        try {
            ScriptObjectMirror so = (ScriptObjectMirror) inv.invokeFunction("myFunc", paras);

            HashMap<String, String> datas = new HashMap<>();
            datas.put("params", so.get(JSSecret.encText).toString());
            datas.put("encSecKey", so.get(JSSecret.encSecKey).toString());
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
