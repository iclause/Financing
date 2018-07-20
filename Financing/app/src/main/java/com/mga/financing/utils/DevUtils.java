package com.mga.financing.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by mga on 2018/7/16 16:36.
 */

public class DevUtils {
    public static final String DEVICE_TYPE="android";

    public static String getImei(Context applicationContext) {
        return ((TelephonyManager) applicationContext.getSystemService(applicationContext.TELEPHONY_SERVICE)).getDeviceId();
    }


}
