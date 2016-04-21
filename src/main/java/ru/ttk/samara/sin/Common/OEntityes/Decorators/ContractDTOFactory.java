package ru.ttk.samara.sin.Common.OEntityes.Decorators;

import ru.ttk.samara.sin.Common.OEntityes.Constants.ODogAttributes;
import ru.ttk.samara.sin.Common.OEntityes.Constants.OResourcePropEnums;
import ru.ttk.samara.sin.Common.OEntityes.Constants.OResources;
import ru.ttk.samara.sin.Common.OEntityes.Constants.UTID;
import ru.ttk.samara.sin.Common.OEntityes.main.OConnection;
import ru.ttk.samara.sin.Common.OEntityes.main.OContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by Admin on 05.09.14.
 */
public class ContractDTOFactory {
    private static DateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy");

    public static OContractCommon_DTO getDTO(OContract contract){
        return (contract.getUtid()== UTID.FL.getCode())?getDTOFL(contract):getDTOUL(contract);
    }

    public static OContractFL_DTO getDTOFL(OContract contract){
        return fillDTOFl(contract);
    }

    public static OContractUL_DTO getDTOUL(OContract contract){
        return fillDTOUl(contract);
    }
    
    
    private static OContractCommon_DTO fillDTOCommon(OContractCommon_DTO target,AbstractOContractDecorator contract){
        target.setAccountNumber(contract.getDognum().toString());
        target.setAccountStatus(contract.getStatus().toString());
        target.setDogNumber(contract.getDogcode());
        target.setDogStartDate(contract.getStartdate()==null?null:dateFormat.format(contract.getStartdate().getTime()));
        target.setDogGroup(contract.getGid().toString());
        target.setDogType(contract.getUtid().toString());
        target.setAdministrativeGroup("");
        target.setAdministrativeDog("");
        target.setAgentName("");
        target.setPaperDogNumber(contract.getAttribute(ODogAttributes.paperDogNumber));
        target.setDogEndDate(contract.getEnddate()==null?null:dateFormat.format(contract.getEnddate().getTime()));
        target.setPhoneNumber(contract.getAttribute(ODogAttributes.phone));
        target.setFactAddress(contract.getAttribute(ODogAttributes.factAddress));
        target.setEmail(contract.getAttribute(ODogAttributes.email));
        target.setBonusReturn(contract.getAttribute(ODogAttributes.bonusReturn));
        target.setbC(contract.getAttribute(ODogAttributes.BC));
        target.setGroup(contract.getGid().toString());
        target.setMagistralGroups("not matched");
        target.setComment(contract.getAttribute(ODogAttributes.comment));
        target.setMobilePhone(contract.getAttribute(ODogAttributes.contactPhoneMob));
        target.setFixedPhone(contract.getAttribute(ODogAttributes.contactPhoneStac));
        target.setDogEndCause("not matched");
        target.setPrice("not matched");
        target.setTaxScheme(contract.getTsid().toString());
        target.setCreditScheme(contract.getCsid().toString());
        target.setBankAccount("not matched");
        target.setBlockDate("not matched");
        return target;
    }
    
    private static OContractFL_DTO fillDTOFl(OContract oContract){
        OContractFL_DTO contractFL_dto=new OContractFL_DTO();
        AbstractOContractDecorator wrappedContract=new AbstractOContractDecorator(oContract);
        contractFL_dto=(OContractFL_DTO)fillDTOCommon(contractFL_dto,wrappedContract);
        contractFL_dto.setName(wrappedContract.getAttribute(ODogAttributes.FIO));
        contractFL_dto.setBirthDay(wrappedContract.getAttribute(ODogAttributes.birstDayDate));
        contractFL_dto.setRegistrationAddress(wrappedContract.getAttribute(ODogAttributes.factAddress));
        contractFL_dto.setSecretWord(wrappedContract.getAttribute(ODogAttributes.secretWord));
        contractFL_dto.setCommonGroups(wrappedContract.getAttribute(ODogAttributes.commonGroupFL));
        contractFL_dto.setPassportData(wrappedContract.getAttribute(ODogAttributes.passportData));
        contractFL_dto.setDirectKTTKContract(wrappedContract.getAttribute(ODogAttributes.MGMN_TTK_DogNumber));
        contractFL_dto.setIsgDTO(getIsgDto(wrappedContract));
        contractFL_dto.setKabinetDTO(getKabinetDto(wrappedContract));

        contractFL_dto.setBirthLocation(wrappedContract.getAttribute(ODogAttributes.birthLocation));

        return  contractFL_dto;
    }

    private static IsgDTO getIsgDto(AbstractOContractDecorator oContract){
        IsgDTO isgDTO = new IsgDTO();
        OConnection connection=null;
        for(OConnection oConnection:oContract.getConnectionList()){
            System.out.println("resuorce code is "+oConnection.getService());
            if(oConnection!=null && oConnection.getResource() !=null  ) {
                if (oConnection.getResource().getId() == OResources.ISGConnection.getId()) {
                    connection = oConnection;
                }
            }
        }
        if (connection!=null && connection.getParameterMap()!=null){
            isgDTO.setLogin(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.login.getId()));
            isgDTO.setPassword(correctPassword(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.password.getId())));
            isgDTO.setBuildingAddress(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.ADDRES_DOM.getId()));
            isgDTO.setFloorNumber(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.etage.getId()));
            isgDTO.setPodezd(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.podjezd.getId()));
            isgDTO.setFlatNumber(connection.getParameterMap().get(OResourcePropEnums.ISGConnection.flatNumber.getId()));
        }
        return isgDTO;
    }

    private static KabinetDTO getKabinetDto(AbstractOContractDecorator oContract){
        KabinetDTO kabinetDTO = new KabinetDTO();
        OConnection connection=null;
        for(OConnection oConnection:oContract.getConnectionList()){
            if(oConnection!=null && oConnection.getResource()!=null) {
                if (oConnection.getResource().getId() == OResources.accesToPersonalCabinet.getId()) {
                    connection = oConnection;
                }
            }
        }
        if (connection!=null && connection.getParameterMap()!=null){
            kabinetDTO.setLogin(connection.getParameterMap().get(OResourcePropEnums.AccesToPersonalCabinet.login.getId()));
            kabinetDTO.setPassword(correctPassword(connection.getParameterMap().get(OResourcePropEnums.AccesToPersonalCabinet.password.getId())));
        }
        return kabinetDTO;
    }

    private static String correctPassword(String password){
        return password==null?null:password.replaceAll("#PLN#","");
    }

    private static OContractUL_DTO fillDTOUl(OContract oContract){
        OContractUL_DTO contractUL_dto=new OContractUL_DTO();
        AbstractOContractDecorator wrappedContract=new AbstractOContractDecorator(oContract);
        contractUL_dto=(OContractUL_DTO)fillDTOCommon(contractUL_dto,wrappedContract);
        contractUL_dto.setShirtName(wrappedContract.getAttribute(ODogAttributes.organisationNameShirt));
        contractUL_dto.setFullName(wrappedContract.getAttribute(ODogAttributes.organisationNameFull));
        contractUL_dto.setInn(wrappedContract.getAttribute(ODogAttributes.inn));
        contractUL_dto.setKpp(wrappedContract.getAttribute(ODogAttributes.kpp));
        contractUL_dto.setUrAddress(wrappedContract.getAttribute(ODogAttributes.addressUr));
        contractUL_dto.setKontaktAdm(wrappedContract.getAttribute(ODogAttributes.contactAdm));
        contractUL_dto.setKontaktTech(wrappedContract.getAttribute(ODogAttributes.contactTech));
        contractUL_dto.setManager(wrappedContract.getAttribute(ODogAttributes.manager));
        contractUL_dto.setDeliveryMethod(wrappedContract.getAttribute(ODogAttributes.deliveryMethod));
        contractUL_dto.setSendDZNotification(wrappedContract.getAttribute(ODogAttributes.sendDZNotification));
        contractUL_dto.setFax(wrappedContract.getAttribute(ODogAttributes.fax));
        return  contractUL_dto;
    }
}
