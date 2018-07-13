package com.mga.financing.http;

import android.util.Log;

import com.mga.financing.safe.Md5Encrypt;

/**
 * Created by mga on 2018/7/10 19:31.
 */

public class SignUtils {
    public static String getSignDomain( Long time,String APPSECRET){
//        Log.d("3sign",String.valueOf(time/1000)+appsecret1);
        String sign= Md5Encrypt.getMD5(String.valueOf(time/1000)+ APPSECRET);
        Log.d("截取前sign", sign+"customappsecret="+ APPSECRET);
        sign=sign.substring(16);
        Log.d("截取后16位3sign",sign);
        return sign;
    }
}
