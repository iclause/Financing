package com.mga.financing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dangelo on 15/11/9.
 */
public class SharedPrefUtil {

    public static boolean getLocationControl(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getBoolean("locationcontrol", true);
    }

    public static void setLocationControl(boolean locationcontrol, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putBoolean("locationcontrol", locationcontrol).commit();
    }
    public static String getDeviceName(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("devicename", "");
    }

    public static void setDeviceType(String device_type, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("device_type", device_type).commit();
    }
    public static String getDeviceType(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("device_type", "");
    }

    public static void setDeviceName(String devicename, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("devicename", devicename).commit();
    }

    public static String getDeviceVersion(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("device_version", "");
    }

    public static void setDeviceVersion(String DeviceVersion, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("device_version", DeviceVersion).commit();
    }
    public static String getImei(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("imei", "");
    }

    public static void setImei(String imei, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("imei", imei).commit();
    }
    public static String getLocation(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("location", "");
    }

    public static void setLocation(String location, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("location", location).commit();
    }
    public static String getDomain(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("domain", "");
    }

    public static void setDomain(String location, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("domain", location).commit();
    }

    public static String getRegistration_id(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("registration_id", "");
    }

    public static void setRegistration_id(String registration_id, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("registration_id", registration_id).commit();
    }

    public static String getImsi(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("imsi", "");
    }

    public static void setImsi(String imsi, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("imsi", imsi).commit();
    }

    public static boolean getAutoAuthLogin(Context mContext, String number) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getBoolean(number+"autoauthlogin", false);
    }

    public static void setAutoAuthLogin(String number, boolean autoLogin, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putBoolean(number+"autoauthlogin", autoLogin).commit();
    }

    public static boolean getUserIsFirstAuth(Context mContext, String number) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getBoolean(number+"userisfirstauth", true);
    }

    public static void setUserIsFirstAuth(String number, boolean userisfirstauth, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putBoolean(number+"userisfirstauth", userisfirstauth).commit();
    }

    public static String getDeadline(Context mContext, String number) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString(number+"deadline", "");
    }

    public static void setDeadline(String number, String deadline, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString(number+"deadline", deadline).commit();
    }

    public static String getSDKVersion(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("ebjarsdkversion", "");
    }

    public static void setSDKVersion(String ebjarsdkversion, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("ebjarsdkversion", ebjarsdkversion).commit();
    }
    public static String getJustAppkey(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("JustAppkey", "");
    }

    public static void setJustAppkey(String JustAppkey, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("JustAppkey", JustAppkey).commit();
    }
    public static String getJustAddress(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        return sp.getString("JustAddress", "");
    }

    public static void setJustAddress(String JustAddress, Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
        sp.edit().putString("JustAddress", JustAddress).commit();
    }
//    public static int getLastAuthOktime(Context mContext, String number) {
//        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
//        return sp.getInt(number, 0);
//    }
//
//    public static void setLastAuthOktime(String number, int lastauthoktime, Context mContext) {
//        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences("ebjarsdk_init_info", Context.MODE_PRIVATE);
//        sp.edit().putInt(number, lastauthoktime).commit();
//    }







}
