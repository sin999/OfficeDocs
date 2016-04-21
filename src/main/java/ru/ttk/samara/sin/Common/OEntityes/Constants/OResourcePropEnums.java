package ru.ttk.samara.sin.Common.OEntityes.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 16.02.14
 * Time: 7:42
 * To change this template use File | Settings | File Templates.
 */
public class OResourcePropEnums {
    public enum AccesToPersonalCabinet {
        oldBillingId(15088),    //ID из старого биллинга
        ttk_stat_comm(14123),   //ttk_stat_comm
        password(14122),        //Пароль
        passChryptMethod(14788),//Метод криптования пароля
        login(14121);           //Логин

        private double id;
        AccesToPersonalCabinet(int id){
            this.id=(double)id;
        }
        public double getId(){
            return id;
        }
    }

    public enum IpSubNetConnection{
        ttk_ip_netcust_comm(14366),
        ttk_ip_netcust(14951),
        ttk_vlan_idn(13864);
        private double id;

        IpSubNetConnection(int id){
            this.id=(double)id;
        }
        public double getId(){
            return id;
        }
        public static IpSubNetConnection getById(Double id){
            IpSubNetConnection retValue=null;
            if (id!=null){
                for (IpSubNetConnection connection: IpSubNetConnection.values()){
                    if (connection.getId() == id){
                        retValue=connection;
                    }
                }
            }
            return retValue;
        }
    }

    public enum ISGConnection {
        linkId(13861),//LinkID
        ttk_ip_nvl(13863),//ttk_ip_nvl
        ttk_static_address(13866),//
        connectionPortNumber(14876),//[ТТК] Номер порта подключения
        shpd_conn_comm(13862),//shpd_conn_comm
        passwordChryptMethod(14788),//Метод криптования пароля
        login(13865),//[ТТК] Логин
        password(13881),//[ТТК] Пароль
        etage(13882),//Этаж
        flatNumber(13870),//Номер квартиры
        addres(15029),//Адрес
        oldBillingId(15088),//ID из старого биллинга
        VLAN(13864),//VLAN
        podjezd(13872),//Подъезд
       ADDRES_DOM(13869);//Подъезд



        private double id;
        ISGConnection(int id){
            this.id=(double)id;
        }
        public double getId(){
            return id;
        }
        public static ISGConnection getById(Double id){
            ISGConnection retValue=null;
            if (id!=null){
                for (ISGConnection isgConnection: ISGConnection.values()){
                    if (isgConnection.getId() == id){
                        retValue=isgConnection;
                    }
                }
            }
            return retValue;
        }
    }

    public enum PhoneNumberConnection{
        ttk_tel_number(14021.0);
        private Double id;
        PhoneNumberConnection(Double id){
            this.id=id;
        }

        public Double getId() {
            return id;
        }

        public static PhoneNumberConnection getById(Double id){
            PhoneNumberConnection retValue=null;
            if (id!=null){
                for (PhoneNumberConnection phoneNumberConnection: PhoneNumberConnection.values()){
                    if (phoneNumberConnection.getId() == id){
                        retValue=phoneNumberConnection;
                    }
                }
            }
            return retValue;
        }
    }
}
