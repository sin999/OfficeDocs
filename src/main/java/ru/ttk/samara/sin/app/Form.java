package ru.ttk.samara.sin.app;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by Admin on 02.09.14.
 */
public class Form {
    private IXDocReport report;
    private IContext context;
    public static Form pojoForm(InputStream in)  throws IOException,XDocReportException{
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
        return new Form(report);
    }

    public static Form xmlForm(InputStream in)  throws IOException,XDocReportException{
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );
        return new Form(report);
    }
    private Form(IXDocReport report) throws IOException,XDocReportException{
        this.report=report;
        context = report.createContext();
    }


    public void putXml(String name,String xml) throws ParserConfigurationException, SAXException, IOException {
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        InputSource projectInputSource = new InputSource( is );
        freemarker.ext.dom.NodeModel o = freemarker.ext.dom.NodeModel.parse( projectInputSource );
        context.put(name, o);
    }

    public void putObject(String name,Object o) {
        context.put(name, o);
    }

    public void saveTo(OutputStream outputStream) throws IOException,XDocReportException{
        report.process(context,outputStream);
    }
}
