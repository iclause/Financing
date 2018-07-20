package com.mga.financing.bean.request;

/**
 * Created by mga on 2018/7/18 18:57.
 */

public class ProductReq {
    private String productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    @Override
    public String toString() {
        return "ProductReq{" +
                "productid='" + productid + '\'' +
                '}';
    }
}
