package com.mga.financing.bean.response;

/**
 * Created by mga on 2018/8/13 19:40.
 */

public class UserinfoRes {

   public String amount;//已购买黄金，单位克
   public String balance;//账户余额，单位元
   public String goldvalue;//已购买的黄金价值单位元

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getGoldvalue() {
        return goldvalue;
    }

    public void setGoldvalue(String goldvalue) {
        this.goldvalue = goldvalue;
    }

    @Override
    public String toString() {
        return "UserinfoRes{" +
                "amount='" + amount + '\'' +
                ", balance='" + balance + '\'' +
                ", goldvalue='" + goldvalue + '\'' +
                '}';
    }
}
