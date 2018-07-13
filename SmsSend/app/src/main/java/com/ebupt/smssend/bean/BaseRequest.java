package com.ebupt.smssend.bean;

/**
 * Created by mga on 2017/11/21 10:54.
 */

public class BaseRequest {
    private String appkey;

    private String timestamp;

    private String sign;

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
}
