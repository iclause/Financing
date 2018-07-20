package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/4/24 16:50.
 */

public class LoginReq {

    /**
     * {
     "bindnumber":"13612341234" ,
     "password":"123456",
     “imei": "864399020227188”,
     “registration_id": "170976fa8a8220f42d4”,
     “device_type": "android”
     }

     */
//   必填参数
    private String bindnumber;
    private String password;
    private String registration_id;
    private String device_type;

//   选填参数
    private String imei;
    private String device_version;
    private String device_name;
    private String locationinfo;

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
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

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getLocationinfo() {
        return locationinfo;
    }

    public void setLocationinfo(String locationinfo) {
        this.locationinfo = locationinfo;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "bindnumber='" + bindnumber + '\'' +
                ", password='" + password + '\'' +
                ", imei='" + imei + '\'' +
                ", registration_id='" + registration_id + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_version='" + device_version + '\'' +
                ", device_name='" + device_name + '\'' +
                ", locationinfo='" + locationinfo + '\'' +
                '}';
    }
}
