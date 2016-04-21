package ru.ttk.samara.sin.Common.OEntityes.Decorators;

/**
 * Created by Admin on 05.09.14.
 */
public class OConnectionDTOCommon {
    private String idFormerBilling;
    private String startDate;
    private String endDate;
    private String tariff;
    private String tariffStartDate;

    public String getIdFormerBilling() {
        return idFormerBilling;
    }

    public void setIdFormerBilling(String idFormerBilling) {
        this.idFormerBilling = idFormerBilling;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getTariffStartDate() {
        return tariffStartDate;
    }

    public void setTariffStartDate(String tariffStartDate) {
        this.tariffStartDate = tariffStartDate;
    }
}
