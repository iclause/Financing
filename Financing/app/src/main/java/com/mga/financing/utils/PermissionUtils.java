package com.mga.financing.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * @author lz.
 * @date on 2018/6/25 14:21.
 */

public class PermissionUtils {

    public static int REQUEST_PERMISSION_READ_CONTACT=100;
    public static int REQUEST_PERMISSION_LOCATION=101;
    public static int REQUEST_PERMISSION_READ_PHONE_STATE=102;
    public static int REQUEST_PERMISSION_AUDIO=103;
    public static int REQUEST_PERMISSION_EXTERNAL_STORAGE=104;
    public static int REQUEST_PERMISSION_LOCATION_FRAGMETN=1010;//不需要回调处理
    public static int REQUEST_PERMISSION_READ_CONTACT_FRAGMETN=1000;//不同回调处理

    private static String TAG=PermissionUtils.class.getSimpleName();

    public static boolean hasPermissions(Context context, @NonNull String... perms) {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }



}
