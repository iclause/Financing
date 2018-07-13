package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/4/24 16:50.
 */

public class LoginRequest {

    /**
     * bindnumber : 13612341234
     * authword : e1
     * imei : 864399020227188
     * registration_id : 170976fa8a8220f42d4
     * device_type : android
     * iosjpushid : com.telinovo.globalphone
     */

    private String bindnumber;
    private String authword;
    private String imei;
    private String registration_id;
    private String device_type;
    private String iosjpushid;

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    public String getAuthword() {
        return authword;
    }

    public void setAuthword(String authword) {
        this.authword = authword;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getIosjpushid() {
        return iosjpushid;
    }

    public void setIosjpushid(String iosjpushid) {
        this.iosjpushid = iosjpushid;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "bindnumber='" + bindnumber + '\'' +
                ", authword='" + authword + '\'' +
                ", imei='" + imei + '\'' +
                ", registration_id='" + registration_id + '\'' +
                ", device_type='" + device_type + '\'' +
                ", iosjpushid='" + iosjpushid + '\'' +
                '}';
    }
}
