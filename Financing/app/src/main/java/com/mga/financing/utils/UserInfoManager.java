package com.mga.financing.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by mga on 2017/6/26 18:28.
 */
public class UserInfoManager {

    private static final String USERINFO = "userinfo";

    public static void saveAccount(Context mContext, String user) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putString("user", user).commit();
    }

    public static String readAccount(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getString("user", "");
    }

    public static void savePassword(Context mContext, String user, String password) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putString("password" + user, password).commit();
    }

    public static String readPassword(Context mContext, String user) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getString("password" + user, "");
    }

    public static void saveIdCard(Context mContext, String user, String idcard) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putString("idcard" + user, idcard).commit();
    }

    public static String readIdCard(Context mContext, String user) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getString("idcard" + user, "");
    }
    public static void saveName(Context mContext, String user, String name) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putString("name" + user, name).commit();
    }

    public static String readName(Context mContext, String user) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getString("name" + user, "");
    }

    public static void saveBankCard(Context mContext, String user, String bankcard) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putString("bankcard" + user, bankcard).commit();
    }

    public static String readBankCard(Context mContext, String user) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getString("bankcard" + user, "");
    }



    public static void setIsAutoLogin(Context mContext, boolean isAutoLogin) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean("isautologin", isAutoLogin).commit();
    }

    public static boolean getIsAutoLogin(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getBoolean("isautologin", false);
    }

    public static void setAlreadyLogin(Context mContext, boolean isAutoLogin) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean("isautologin", isAutoLogin).commit();
    }

    /**
     * 成功登录过app的标识
     *
     * @param mContext
     * @return
     */
    public static boolean getAlreadyLogin(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getBoolean("isautologin", false);
    }


    public static void setIsRegister(Context mContext, boolean isRegister) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean("isregister", isRegister).commit();
    }

    /**
     * 是否已经成功注册过
     *
     * @param mContext
     */
    public static boolean getIsRegister(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(USERINFO, Context.MODE_PRIVATE);
        return sp.getBoolean("isregister", false);
    }

    public static void clearUserLoginInfo(Context context, String account) {
        savePassword(context.getApplicationContext(), account, "");
        saveAccount(context.getApplicationContext(), "");
        setIsAutoLogin(context.getApplicationContext(), false);
    }

    public static void saveUserLoginInfo(Context context, String account, String password) {
        savePassword(context.getApplicationContext(), account, password);
        saveAccount(context.getApplicationContext(), account);
        setIsAutoLogin(context.getApplicationContext(), true);
        UserInfoManager.setAlreadyLogin(context.getApplicationContext(), true);
    }
}
