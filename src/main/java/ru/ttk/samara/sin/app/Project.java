package ru.ttk.samara.sin.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.08.14.
 */
public class Project {

    private final String name;
    private List<String> nameList=new ArrayList<String>();

    public Project(String name) {
        for (int i=0;i<10;i++){
            nameList.add(name+i);
        }

        for (String str:nameList){
            System.out.println(str);
        }
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
