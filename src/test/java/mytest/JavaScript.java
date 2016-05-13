package mytest;

import javax.script.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
public class JavaScript {
    public static void main(String[] args) throws Exception{

        runJavascript();
        ping();
    }

    public static void runJavascript() throws Exception{
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bindings = engine.createBindings();
        bindings.put("factor", 1);
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        Scanner input = new Scanner(System.in);
        while (input.hasNextInt()) {
            int first = input.nextInt();
            int sec = input.nextInt();
            System.out.println("输入参数，first=" + first + ",sec=" + sec);

            engine.eval(new FileReader("g:/model.js"));
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;

                Double result = (Double) in.invokeFunction("formula", first, sec);
                System.out.println("运算结果为：" + result.intValue());
            }
        }
    }

    public static void ping() throws Exception{
        String cmd="cmd.exe ping ";
        String ipprefix="192.168.10.";
        int begin=101;
        int end=200;
        Process p;
        for(int i=begin;i<end;i++){
            p= Runtime.getRuntime().exec(cmd+ipprefix+i);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while((line = reader.readLine()) != null)
            {
                //Handling line , may logs it.
                System.out.println(line);
            }
            reader.close();
            p.destroy();
        }
    }
}
