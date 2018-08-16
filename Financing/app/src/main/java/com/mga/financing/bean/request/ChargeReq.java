package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/8/13 19:29.
 */

public class ChargeReq {

    public String bindnumber;
    public String amount;//充值金额，单位元

    public String getBindnumber() {
        return bindnumber;
    }

    public void setBindnumber(String bindnumber) {
        this.bindnumber = bindnumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ChargeReq{" +
                "bindnumber='" + bindnumber + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
