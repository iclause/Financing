package com.mga.financing.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mga on 2017/6/26 17:35.
 */
public class ToastUtil {
    public static void show(Context context, String content){
        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
