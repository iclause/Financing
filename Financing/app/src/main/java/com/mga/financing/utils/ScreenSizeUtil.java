package com.mga.financing.utils;

import android.app.Activity;

/**
 * Created by mga on 2018/4/9 17:00.
 */

public class ScreenSizeUtil {
    public static int getScreenWidth(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }
}
