package ru.ttk.samara.sin.app;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Named;
import java.io.File;

/**
 * Created by Admin on 29.08.14.
 */
@Named
public class App2 {
    public static void main(String[] args) {
        if (args.length>1){
            File templateFile=new File(args[1]);
            if (templateFile.exists()){
                    String outFileName=args[1].replaceAll(".docx$","["+args[0]+"].docx");
                System.out.println("Out file name is "+outFileName);
                    File outFile=new File(outFileName);
                Weld weld = new Weld();
                WeldContainer container = weld.initialize();
                App1 app1 = container.instance().select(App1.class).get();
                app1.run(args[0],templateFile,outFile);
                weld.shutdown();
            }else {
                System.out.println("template "+args[0]+" not found!");
            }
        }else{
            System.out.println("usage - parser dogNumber templateFileName.docx");
        }
    }


}
