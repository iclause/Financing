package com.mga.financing.http;

/**
 * Created by mga on 2018/7/16 15:25.
 */

public class NetCode {
    public static final int SUCCESS = 00000000;
    //公共响应码
    public static final int SERVICE_ERROR = 30000001;
    //注册
    public static final int USER_ALREADY_OPEN_ACCOUN = 20000012;
    public static final int USER_OPEN_ACCOUNT_FAILED = 20000018;
    //登录
    public static final int USER_NOT_OPEN_ACCOUNT = 20000013;
    public static final int USER_PASSWORD_ERROR = 20000019;
    //产品买入/产品卖出/用户账户查询
    public static final int USER_DOES_NOT_EXIST = 20000030;
    public static final int PRODUCT_DOES_NOT_EXIST = 20000031;
    public static final int OTHER_REASON_FAIL = 20000032;
    //产品查询
    public static final int PRODUCTS_IS_NULL = 40000003;


}
