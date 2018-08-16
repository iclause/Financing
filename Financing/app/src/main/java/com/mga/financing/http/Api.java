package com.mga.financing.http;

/**
 * Created by mga on 2018/7/12 15:18.
 */

public class Api {
//   ------------------ 主机地址------------------
    public static final String HOST = "http://47.94.107.221:8081/gold_wallet/v1/";//内测公网地址

//    ------------------接口名称------------------
    public static final String LOGIN = "user_login";
    public static final String REGIST = "user_openaccount";
    public static final String ISREGIST = "user_select";
    public static final String LIST_PRODUCT = "list_product";
    public static final String LIST_ALLPRODUCT = "list_allproduct";
    public static final String BUY_PRODUCT = "buy_product";
    public static final String SELL_PRODUCT = "sell_product";
    public static final String GOLDPRICE = "goldprice";
    public static final String DEPOSIT = "deposit";//充值
    public static final String QUERY_USER = "query_user";	//用户账户查询


//    ------------------特殊接口携带参数------------------
    public static final String PARAM_NEWER_PRODUCT = "recommend";//productid参数为recommend时获取推荐活动列表
}
