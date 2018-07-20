package com.mga.financing.utils;

import android.content.Context;
import android.content.Intent;

import com.mga.financing.R;
import com.mga.financing.http.NetCode;
import com.mga.financing.ui.MAlertDialog;


/**
 * Created by mga on 2017/9/19 17:00.
 */

public class NetCodeShowUtil {
    /**
     * 显示错误信息点击取消跳转至某界面
     */
    public static void showAndNeJump(Context context, int code, Intent intent) {
        switch (code) {
//            public static final int SERVICE_ERROR = 30000001;
//            //注册
//            public static final int USER_ALREADY_OPEN_ACCOUN = 20000012;
//            public static final int USER_OPEN_ACCOUNT_FAILED = 20000018;
//            //登录
//            public static final int USER_NOT_OPEN_ACCOUNT = 20000013;
//            public static final int USER_PASSWORD_ERROR = 20000019;
//            //产品买入/产品卖出/用户账户查询
//            public static final int USER_DOES_NOT_EXIST = 20000030;
//            public static final int PRODUCT_DOES_NOT_EXIST = 20000031;
//            public static final int OTHER_REASON_FAIL = 20000032;
            case NetCode.SERVICE_ERROR:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.SERVERERRORSTR) + "(" + code + ")", intent);
                break;
            case NetCode.USER_OPEN_ACCOUNT_FAILED:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.USER_OPEN_ACCOUNT_FAILED) + "(" + code + ")", intent);
                break;
            case NetCode.USER_NOT_OPEN_ACCOUNT:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.USER_NOT_OPEN_ACCOUNT) + "(" + code + ")", intent);
                break;
            case NetCode.USER_PASSWORD_ERROR:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.USER_PASSWORD_ERROR) + "(" + code + ")", intent);
                break;
            case NetCode.USER_DOES_NOT_EXIST:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.USER_DOES_NOT_EXIST) + "(" + code + ")", intent);
                break;
            case NetCode.PRODUCT_DOES_NOT_EXIST:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.PRODUCT_DOES_NOT_EXIST) + "(" + code + ")", intent);
                break;
            default:
                MAlertDialog.showErrorDialog(context, context.getResources().getString(R.string.MISS_MISTAKES) + "(" + code + ")", intent);
                break;



        }
    }
}
