package com.mga.financing.bean.response;


/**
 * Created by mga on 2018/5/10 18:38.
 */

public class ProductRes  {
    public String productid; //eg:产品id
    public String productdesc; //eg:
    public String productname;   //eg:1元起购，随时买卖
    public String annualizedrate;  //eg:1.0%
    public String annualizedratedes;  //eg:1.0%
    public String productslogan;  //eg:目标年化回报率
    public String cycle;  //eg:1~10个月
    public String fathermenu;  //eg:类型
    public String producttype;  //eg:
    public String h5url;  //

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getAnnualizedrate() {
        return annualizedrate;
    }

    public void setAnnualizedrate(String annualizedrate) {
        this.annualizedrate = annualizedrate;
    }

    public String getAnnualizedratedes() {
        return annualizedratedes;
    }

    public void setAnnualizedratedes(String annualizedratedes) {
        this.annualizedratedes = annualizedratedes;
    }

    public String getProductslogan() {
        return productslogan;
    }

    public void setProductslogan(String productslogan) {
        this.productslogan = productslogan;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getFathermenu() {
        return fathermenu;
    }

    public void setFathermenu(String fathermenu) {
        this.fathermenu = fathermenu;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getH5url() {
        return h5url;
    }

    public void setH5url(String h5url) {
        this.h5url = h5url;
    }

    @Override
    public String toString() {
        return "ProductRes{" +
                "productid='" + productid + '\'' +
                ", productdesc='" + productdesc + '\'' +
                ", productname='" + productname + '\'' +
                ", annualizedrate='" + annualizedrate + '\'' +
                ", annualizedratedes='" + annualizedratedes + '\'' +
                ", productslogan='" + productslogan + '\'' +
                ", cycle='" + cycle + '\'' +
                ", fathermenu='" + fathermenu + '\'' +
                ", producttype='" + producttype + '\'' +
                ", h5url='" + h5url + '\'' +
                '}';
    }
}
