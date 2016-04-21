package ru.ttk.samara.sin.Common.OEntityes.Decorators;

/**
 * Created by Admin on 05.09.14.
 */
public class OContractUL_DTO extends OContractCommon_DTO {

    private String shirtName;
    private String fullName;
    private String inn;
    private String kpp;
    private String urAddress;
    private String kontaktAdm;
    private String kontaktTech;
    private String manager;
    private String deliveryMethod;
    private String sendDZNotification;
    private String fax;

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getUrAddress() {
        return urAddress;
    }

    public void setUrAddress(String urAddress) {
        this.urAddress = urAddress;
    }

    public String getKontaktAdm() {
        return kontaktAdm;
    }

    public void setKontaktAdm(String kontaktAdm) {
        this.kontaktAdm = kontaktAdm;
    }

    public String getKontaktTech() {
        return kontaktTech;
    }

    public void setKontaktTech(String kontaktTech) {
        this.kontaktTech = kontaktTech;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getSendDZNotification() {
        return sendDZNotification;
    }

    public void setSendDZNotification(String sendDZNotification) {
        this.sendDZNotification = sendDZNotification;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
