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








}
