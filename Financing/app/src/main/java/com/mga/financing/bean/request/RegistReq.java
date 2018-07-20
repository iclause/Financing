package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/4/24 16:50.
 */

public class RegistReq {


    /**
     * userid : ad4a54fa5df5ad5fa5
     * nickname : zhangsan
     * bindnumber : 13612341234
     * verificationcode : 654321
     * password : e10adc3949ba59abbe56e057f20f883e
     * imei : 864399020227188
     * registration_id : 170976fa8a8220f42d4
     * device_type : android
     */

    private String userid;
    private String nickname;
    private String bindnumber;
    private String verificationcode;
    private String password;
    private String imei;
    private String registration_id;
    private String device_type;
    private String device_version;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDevice_version() {
        return device_version;
    }

    public void setDevice_version(String device_version) {
        this.device_version = device_version;
    }

    @Override
    public String toString() {
        return "RegistRequest{" +
                "userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bindnumber='" + bindnumber + '\'' +
                ", verificationcode='" + verificationcode + '\'' +
                ", password='" + password + '\'' +
                ", imei='" + imei + '\'' +
                ", registration_id='" + registration_id + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_version='" + device_version + '\'' +
                '}';
    }
}
