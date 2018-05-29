package com.mga.financing.bean;

import com.mga.financing.base.bean.BaseNet;

/**
 * Created by mga on 2018/5/10 18:38.
 */

public class ProductBean extends BaseNet {
    public String proName; //eg:流动金
    public String proDes;   //eg:1元起购，随时买卖
    public String upDownNum;  //eg:1.0%
    public String proDes1;  //eg:目标年化回报率
    public String circle;  //eg:1~10个月
    public int headertype;  //eg:类型1 ：稳定生产 省时省力

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDes() {
        return proDes;
    }

    public void setProDes(String proDes) {
        this.proDes = proDes;
    }

    public String getUpDownNum() {
        return upDownNum;
    }

    public void setUpDownNum(String upDownNum) {
        this.upDownNum = upDownNum;
    }

    public String getProDes1() {
        return proDes1;
    }

    public void setProDes1(String proDes1) {
        this.proDes1 = proDes1;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public int getHeadertype() {
        return headertype;
    }

    public void setHeadertype(int headertype) {
        this.headertype = headertype;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "proName='" + proName + '\'' +
                ", proDes='" + proDes + '\'' +
                ", upDownNum='" + upDownNum + '\'' +
                ", proDes1='" + proDes1 + '\'' +
                ", circle='" + circle + '\'' +
                ", headertype=" + headertype +
                '}';
    }
}
