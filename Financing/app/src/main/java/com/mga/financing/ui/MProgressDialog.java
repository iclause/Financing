package com.mga.financing.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by mga on 2017/6/26 10:28.
 */
public class MProgressDialog {
    private static ProgressDialog mProgressDialog;
    private static final String TAG=MProgressDialog.class.getName();

    public static void show(Context context, String content){
        if(mProgressDialog==null||!mProgressDialog.isShowing()){
            mProgressDialog = ProgressDialog.show(context, null,
                    content);
            Log.d(TAG, "mProgressDialog,show");
        }
    }
    public static void cancle(){
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog=null;
            Log.i(TAG, "mProgressDialog.dismiss()");
        }
    }
}
