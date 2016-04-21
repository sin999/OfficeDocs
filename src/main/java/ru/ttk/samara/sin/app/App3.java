package ru.ttk.samara.sin.app;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Named;

/**
 * Created by Admin on 18.12.14.
 */
@Named
public class App3 extends AbstractApp{
    public static void main(String[] args) {
        Weld weld = new Weld();
//        WeldContainer container = weld.initialize();
//       gedWeld();
//        App3 app3 = getBean(App3.class);
//        app3.doWork();
    }

    public void doWork(){
        System.out.println("It works!");
    }
}
