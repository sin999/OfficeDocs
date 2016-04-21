
package ru.ttk.samara.sin.servlets;

import fr.opensagres.xdocreport.core.XDocReportException;
import org.apache.commons.io.FilenameUtils;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.ContractDTOFactory;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.OContractFL_DTO;
import ru.ttk.samara.sin.Common.OEntityes.main.OContract;
import ru.ttk.samara.sin.app.Form;
import ru.ttk.samara.sin.onyma.Services.ContractService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 02.09.14.
 * /documents/Договор ФЛ(631602460).docx
 */

@WebServlet(name = "testServlet", urlPatterns = {"/documents/*"})
public class DocServlet  extends HttpServlet {
    @Inject
    private ContractService contractService;
    private String templateFileName="C:\\Users\\Admin\\Documents\\reports\\Договор ФЛ.docx";

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contractNumber=getFetchDogNumberFromFileName(uri);
        if (contractNumber==null)contractNumber= getFetchDogNumberFromDirName(uri);
        OContractFL_DTO contractFL=getContractFL(contractNumber);
        if ( contractFL!=null && isDocRequested(uri) ){
            writeDoc(resp.getOutputStream(),contractFL,new File(templateFileName));
        }else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<HTML>");
            out.println("<BODY>");
            out.println("Contract with number "+contractNumber+" wasn't found !!!");
            out.println("</BODY>");
            out.println("</HTML>");

        }
    }

    private void writeDoc(OutputStream outputStream,OContractFL_DTO contractFL, File templateFile){
        try {
            InputStream in = new FileInputStream(templateFile);
            Form form = Form.pojoForm(in);
            form.putObject("contractFL", contractFL);
            form.saveTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

    OContractFL_DTO getContractFL(String dogNumber){
        OContract contract=contractService.getFullContract(dogNumber);
        return contract==null?null:(ContractDTOFactory.getDTOFL(contract));
    }

    private boolean isDocRequested(String uri){
        return uri!=null && uri.trim().endsWith(".docx");
    }

    private String getFetchDogNumberFromDirName(String uri){
        String docNum=null;
        String[] arr = uri.split("/");
        int i=0;
        for(String part:arr){
            docNum+=" "+(i++)+" >> "+part;
        }
        return arr.length<3?null:arr[2];
//        return docNum;
    }

    private static String getFetchDogNumberFromFileName(String uri){
        String docNum=null;
        String REGEX="\\([^()]*\\)$";
        Pattern p = Pattern.compile(REGEX);
        if(FilenameUtils.getExtension(uri)!=""){
            String baseName=FilenameUtils.getBaseName(uri);
            Matcher m = p.matcher(baseName);
            while (m.find()){
                docNum=m.group();
            }
        }
        return docNum==null?null:docNum.replaceAll("\\(","").replaceAll("\\)","");
    }

}

