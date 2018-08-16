package com.mga.financing.bean.response;

/**
 * Created by mga on 2018/8/13 18:22.
 */

public class QueryGoldenRes {
    public String goldprice;

    public String getGoldprice() {
        return goldprice;
    }

    public void setGoldprice(String goldprice) {
        this.goldprice = goldprice;
    }

    @Override
    public String toString() {
        return "QueryGoldenRes{" +
                "goldprice='" + goldprice + '\'' +
                '}';
    }
}
