package ru.ttk.samara.sin.onyma.Services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Admin on 03.04.14.
 */
public class Xmler {
    public static String marshalObject(Object o, Class clazz){
        String xml="";
        if (o!=null && clazz!=null){
            try{
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                // output pretty printed
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING,"windows-1251");
                jaxbMarshaller.marshal(o,outputStream);
                xml = new String(outputStream.toByteArray(),"windows-1251");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  xml;
    }

    public static <T> T unmarshalObject(String xml,Class clazz){
        T obj=null;
        if(xml!=null && clazz!=null){
            try {
                InputStream inputStream=new ByteArrayInputStream(xml.getBytes("windows-1251"));
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    obj = (T) jaxbUnmarshaller.unmarshal(inputStream);

                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return obj;
    }
}
