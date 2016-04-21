package ru.ttk.samara.sin.Common.OEntityes.Decorators;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Admin on 01.09.14.
 */
@XmlRootElement
public class OContractFL_DTO extends OContractCommon_DTO {

    private String name;
    private String birthDay;
    private String registrationAddress;
    private String secretWord;
    private String commonGroups;
    private String passportData;
    private String directKTTKContract;
    private IsgDTO isgDTO=new IsgDTO();
    private KabinetDTO kabinetDTO=new KabinetDTO();

    private String birthLocation;


    public String getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(String birthLocation) {
        this.birthLocation = birthLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getCommonGroups() {
        return commonGroups;
    }

    public void setCommonGroups(String commonGroups) {
        this.commonGroups = commonGroups;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public String getDirectKTTKContract() {
        return directKTTKContract;
    }

    public void setDirectKTTKContract(String directKTTKContract) {
        this.directKTTKContract = directKTTKContract;
    }

    public IsgDTO getIsgDTO() {
        return isgDTO;
    }

    public void setIsgDTO(IsgDTO isgDTO) {
        this.isgDTO = isgDTO;
    }

    public KabinetDTO getKabinetDTO() {
        return kabinetDTO;
    }

    public void setKabinetDTO(KabinetDTO kabinetDTO) {
        this.kabinetDTO = kabinetDTO;
    }
}
