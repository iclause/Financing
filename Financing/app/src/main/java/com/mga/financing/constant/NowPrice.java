package com.mga.financing.constant;

import android.content.Context;

import com.mga.financing.utils.SharedPrefUtil;

/**
 * Created by mga on 2018/6/20 18:58.
 */

public class NowPrice {
    public static String price="0"; //实时金价
    public static String walletBalance="00.00"; //账户余额


    public static String getPrice(Context context){
        if("0".equals(price)){
          return  SharedPrefUtil.getNowGoldenPrice(context);
        }
        return price;
    }
}
