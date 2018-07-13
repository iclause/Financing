package com.ebupt.smssend.bean;

/**
 * 基本类，单元类
 * Created by Administrator on 2017/4/13.
 */
public class BaseNet {
    private String appkey;

    private String timestamp;

    private String sign;

    private String code;

    private String description;

    public BaseNet() {
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
