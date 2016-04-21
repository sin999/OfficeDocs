package ru.ttk.samara.sin.app;

import fr.opensagres.xdocreport.core.XDocReportException;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.ContractDTOFactory;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.OContractFL_DTO;
import ru.ttk.samara.sin.Common.OEntityes.main.OContract;
import ru.ttk.samara.sin.onyma.Services.ContractService;

import javax.inject.Inject;
import java.io.*;

/**
 * Created by Admin on 29.08.14.
 */
public class App1 {
    @Inject
    private ContractService contractService;







    public void run(String dogNum,File templateFile,File targetFile)
   {

//       OContract contract=contractService.getFullContract("631602460");
       OContract contract=contractService.getFullContract(dogNum);
       System.out.println(contract==null?" Contract appiried to be null!":"Balance is "+contract.getBalance());
       OContractFL_DTO contractFL = ContractDTOFactory.getDTOFL(contract);
//       System.out.println(contractFL.getName());
//       System.out.println(Xmler.marshalObject(contractFL,OContractFL_DTO.class));


       try {
            // 1) Load Docx file by filling Velocity template engine and cache it to the registry
//            InputStream in = App1.class.getResourceAsStream("D:\\reports\\test1.docx");

//           InputStream in = new FileInputStream("C:\\Users\\Admin\\Documents\\reports\\Договор ФЛ.docx");
//           OutputStream out = new FileOutputStream(new File("C:\\Users\\Admin\\Documents\\reports\\test1_Out.docx"));
           InputStream in = new FileInputStream(templateFile);
           OutputStream out = new FileOutputStream(targetFile);
           Form form = Form.pojoForm(in);
           form.putObject("contractFL", contractFL);
           form.saveTo(out);

//           IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//           IContext context = report.createContext();
//           context.put("contractFL", contractFL);
//           report.process(context, out);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

}
