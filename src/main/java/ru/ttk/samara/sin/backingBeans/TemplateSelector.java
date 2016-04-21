package ru.ttk.samara.sin.backingBeans;

import fr.opensagres.xdocreport.core.XDocReportException;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.ContractDTOFactory;
import ru.ttk.samara.sin.Common.OEntityes.Decorators.OContractFL_DTO;
import ru.ttk.samara.sin.Common.OEntityes.main.OContract;
import ru.ttk.samara.sin.app.Form;
import ru.ttk.samara.sin.onyma.Services.ContractService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 04.09.14.
 */
@Named
@SessionScoped
public class TemplateSelector implements Serializable{
    @Inject
    private ContractService contractService;
    private TreeNode selectedNode;

    private OContract oContract;
//    private static String rootDirPath="D:\\Users\\Admin\\Documents\\reports\\";
    private static String rootDirPath="/var/sss/docs/";

    public StreamedContent getFile() {
       String path= ((TreeElement)(selectedNode.getData())).getPath();
        InputStream stream = writeDoc(oContract,new File(path));
        String filename=((TreeElement)(selectedNode.getData())).getName();
        try {
            // Encoding
            filename = URLEncoder.encode(filename, "UTF-8").replaceAll(
                    "\\+", "%20");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        StreamedContent file = new DefaultStreamedContent(stream, "image/jpg", filename);
        return file;
    }

    public void download(){
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", ((TreeElement)(selectedNode.getData())).getName());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private  InputStream writeDoc(OContract contract, File templateFile){
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            InputStream in = new FileInputStream(templateFile);
            Form form = Form.pojoForm(in);
            form.putObject("contractFL", ContractDTOFactory.getDTOFL(contract));
            form.putObject("contractUL", ContractDTOFactory.getDTOUL(contract));
            form.putObject("contract", ContractDTOFactory.getDTO(contract));
            form.saveTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }



    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    private String getDog(){
        Map<String, String> params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String dogNum = params.get("dogNum");
        OContract contract=getContract(dogNum);
        return contract==null?null:contract.getDogcode();

    }
    
    private OContract getContract(String dogNumber){
        if(dogNumber!=null && !dogNumber.equals("")){
            if (oContract ==null || !(dogNumber.equals(oContract.getDogcode()))){
                oContract = contractService.getFullContract(dogNumber);
            }
        }
        return oContract;
    }




    public TreeNode getTreeModel(){
        TreeNode root = new DefaultTreeNode(new TreeElement("root","-","folder"), null);
        File dir=new File(rootDirPath);
        fileSystem2TreeModel(dir,root);
        return root;
    }

    private void fileSystem2TreeModel(File rootDir,TreeNode rootNode){
        MyDir myDir=new MyDir(rootDir);
        for(File dir:myDir.getSubDirs()){
            TreeNode root = new DefaultTreeNode(new TreeElement(dir.getName(),"-","folder"), rootNode);
            fileSystem2TreeModel(dir,root);
        }

        for (File file:myDir.getTemplateList()){
            new DefaultTreeNode("document",new TreeElement(improveFileName(file.getName()),file.getAbsolutePath(),"document"), rootNode);
        }
    }

    private String improveFileName(String filename){
        String extension=FilenameUtils.getExtension(filename);
        String body=FilenameUtils.getBaseName(filename);
        String path=FilenameUtils.getPath(filename);
        return (extension==null || !extension.equals("docx") || getDog()==null)?filename:path+body+"_"+getDog()+"_."+extension;
    }

    public String getApplicationUri() {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        request.getRequestURL();
        request.getRequestURI();
        return "path is "+FilenameUtils.getFullPath(request.getRequestURI())+" name is "+FilenameUtils.getName(request.getRequestURI()) ;

    }

    private String getFetchDogNumberFromDirName(String uri){
        String docNum=null;
        String[] arr = uri.split("/");
        int i=0;
        return arr.length<3?null:arr[2];
    }
    class MyDir{
        private File dir;
        MyDir(File dir){
            this.dir=dir;
        }

        public List<File> getSubDirs(){
            List<File> subDirs=new ArrayList<File>();
                for (File file : dir.listFiles()) {
                    if (file.isDirectory()) {
                        subDirs.add(file);
                    }
                }
            return subDirs;
        }
        public List<File> getTemplateList(){
            List<File> subDirs=new ArrayList<File>();
            for (File file:dir.listFiles()){
                if(file.isFile()){
                    subDirs.add(file);
                }
            }
            return subDirs;
        }
    }

    public OContract getoContract() {
        return oContract;
    }

    public void setoContract(OContract oContract) {
        this.oContract = oContract;
    }
}
