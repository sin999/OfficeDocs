package ru.ttk.samara.sin.app;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Named;

/**
 * Created by Admin on 18.12.14.
 */
@Named
public class AbstractApp {
    private static WeldContainer container;
    public static WeldContainer gedWeld(){
        if (container ==null){
            try{
            Weld weld = new Weld();
            container = weld.initialize();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return container;
    }

    public static <T> T getBean(Class clazz){
        return (T)(gedWeld().instance().select(clazz).get());
    }

}
