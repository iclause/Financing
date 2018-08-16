package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/7/20 18:14.
 */

public class SellReq {
    private String userid;
    private String productid ;
    private String number ;
    private String orderid ;
    private String bindnumber ;
    private String timestamp ;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BuyReq{" +
                "userid='" + userid + '\'' +
                ", productid='" + productid + '\'' +
                ", number='" + number + '\'' +
                ", orderid='" + orderid + '\'' +
                ", bindnumber='" + bindnumber + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
