package ru.ttk.samara.sin.Common.OEntityes.Decorators;



import ru.ttk.samara.sin.Common.OEntityes.Constants.ODogAttributes;
import ru.ttk.samara.sin.Common.OEntityes.SimplePayment;
import ru.ttk.samara.sin.Common.OEntityes.main.OAuthName;
import ru.ttk.samara.sin.Common.OEntityes.main.OConnection;
import ru.ttk.samara.sin.Common.OEntityes.main.OContract;
import ru.ttk.samara.sin.Common.OEntityes.main.OContractAttribute;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13.02.14
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class AbstractOContractDecorator extends OContract implements Serializable {
    private OContract oContract;
    public AbstractOContractDecorator(OContract oContract) {
     this.oContract=oContract;
    }

    @Override
    public Double getBill() {
        return oContract==null?null:oContract.getBill();
    }

    @Override
    public Calendar getClose_billing_date() {
        return oContract==null?null:oContract.getClose_billing_date();
    }

    @Override
    public List<OConnection> getConnectionList() {
        return oContract==null?null:oContract.getConnectionList();
    }

    @Override
    public Map<Double, OContractAttribute> getContractAttributeMap() {
        return oContract==null?null:oContract.getContractAttributeMap();
    }

    @Override
    public Double getCsid() {
        return oContract==null?null:oContract.getCsid();
    }

    @Override
    public String getDogcode() {
        return oContract==null?null:oContract.getDogcode();
    }

    @Override
    public Double getDogid() {
        return oContract==null?null:oContract.getDogid();
    }

    @Override
    public Double getDogidup() {
        return oContract==null?null:oContract.getDogidup();
    }

    @Override
    public Double getDognum() {
        return oContract==null?null:oContract.getDognum();
    }

    @Override
    public Calendar getEnddate() {
        return oContract==null?null:oContract.getEnddate();
    }

    @Override
    public Double getGid() {
        return oContract==null?null:oContract.getGid();
    }

    @Override
    public Double getOrgid() {
        return oContract==null?null:oContract.getOrgid();
    }

    @Override
    public Double getParent_orgid() {
        return oContract==null?null:oContract.getParent_orgid();
    }

    @Override
    public List<SimplePayment> getPaymentList() {
        return oContract==null?null:oContract.getPaymentList();
    }

    @Override
    public Calendar getStartdate() {
        return oContract==null?null:oContract.getStartdate();
    }

    @Override
    public Double getStatus() {
        return oContract==null?null:oContract.getStatus();
    }

    @Override
    public Calendar getStatus_date() {
        return oContract==null?null:oContract.getStatus_date();
    }

    @Override
    public Double getTsid() {
        return oContract==null?null:oContract.getTsid();
    }

    @Override
    public Double getUtid() {
        return oContract==null?null:oContract.getUtid();
    }

    @Override
    public Double getWhomake() {
        return oContract==null?null:oContract.getWhomake();
    }

    @Override
    public void setBill(Double bill) {
        oContract.setBill(bill);
    }

    @Override
    public void setClose_billing_date(Calendar close_billing_date) {
        oContract.setClose_billing_date(close_billing_date);
    }

    @Override
    public void setCsid(Double csid) {
        oContract.setCsid(csid);
    }

    @Override
    public void setDogcode(String dogcode) {
        oContract.setDogcode(dogcode);
    }

    @Override
    public void setDogid(Double dogid) {
        oContract.setDogid(dogid);
    }

    @Override
    public void setDogidup(Double dogidup) {
        oContract.setDogidup(dogidup);
    }

    @Override
    public void setDognum(Double dognum) {
        oContract.setDognum(dognum);
    }

    @Override
    public void setEnddate(Calendar enddate) {
        oContract.setEnddate(enddate);
    }

    @Override
    public void setGid(Double gid) {
        oContract.setGid(gid);
    }

    @Override
    public void setOrgid(Double orgid) {
        oContract.setOrgid(orgid);
    }

    @Override
    public void setParent_orgid(Double parent_orgid) {
        oContract.setParent_orgid(parent_orgid);
    }

    @Override
    public void setStartdate(Calendar startdate) {
        oContract.setStartdate(startdate);
    }

    @Override
    public void setStatus(Double status) {
        oContract.setStatus(status);
    }

    @Override
    public void setStatus_date(Calendar status_date) {
        oContract.setStatus_date(status_date);
    }

    @Override
    public void setTsid(Double tsid) {
        oContract.setTsid(tsid);
    }

    @Override
    public void setUtid(Double utid) {
        oContract.setUtid(utid);
    }

    @Override
    public void setWhomake(Double whomake) {
        oContract.setWhomake(whomake);
    }

    @Override
    public List<OAuthName> getAuthNameList() {
        return oContract.getAuthNameList();
    }

    @Override
    public Double getBalance() {
        return oContract.getBalance();
    }

    protected String getAttribute(ODogAttributes attributeType){
        OContractAttribute dogAttribute = getContractAttributeMap().get(attributeType.getId());
        return dogAttribute==null || dogAttribute.getValue()==null?" - ":dogAttribute.getValue();
    }


}
