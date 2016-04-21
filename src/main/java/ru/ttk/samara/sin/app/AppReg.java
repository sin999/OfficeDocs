package ru.ttk.samara.sin.app;

import freemarker.template.utility.StringUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 03.09.14.
 */
public class AppReg {
    public static void main(String[] args) {


        String fileName="Договор ФЛ(631602460)(631602461).docx";
        String baseName= FilenameUtils.getBaseName(fileName);

        System.out.println("Base name is >"+getFetchDogNumberFromFileName(fileName)+"<");
    }

    private static String getFetchDogNumberFromFileName(String uri){
        System.out.println("Uri is >"+uri+"<");
        String docNum=null;
        String REGEX="\\([^()]*\\)$";
        System.out.println("Regexp  is >"+REGEX+"<");
        Pattern p = Pattern.compile(REGEX);
        if(FilenameUtils.getExtension(uri)!=""){
            String baseName=FilenameUtils.getBaseName(uri);
            System.out.println("BaseName is >"+baseName+"<");
            Matcher m = p.matcher(baseName);
            while (m.find()){
                docNum=m.group();
            }
        }
        return docNum==null?"":docNum.replaceAll("\\(","").replaceAll("\\)","");
    }

//    public static String substringBetween(String str){
//        String retValue=null;
//        String[] arr=(str==null)?null: StringUtils.substringsBetween(str,"(",")");
//        if (arr!=null && arr.length>0){
//            retValue=
//        }
//
//    }
}
