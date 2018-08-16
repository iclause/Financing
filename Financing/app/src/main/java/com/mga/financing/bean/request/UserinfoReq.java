package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/8/13 19:39.
 */

public class UserinfoReq {
    public String userid;
    public String bindnumber;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    @Override
    public String toString() {
        return "UserinfoReq{" +
                "userid='" + userid + '\'' +
                ", bindnumber='" + bindnumber + '\'' +
                '}';
    }
}
