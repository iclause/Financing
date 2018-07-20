package com.mga.financing.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.ebupt.jlog.JLog;
import com.mga.financing.R;


/**
 * Created by mga on 2017/9/19 16:15.
 */

public class MAlertDialog {
    private static String TAG=MAlertDialog.class.getSimpleName();

    public static void showErrorDialog(final Context context, final String content, final Intent intent){
        final Activity activity=(Activity)context;
        new AlertDialog.Builder(context)
                .setMessage(content)
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(intent!=null) {
                            context.startActivity(intent);


                        }else{
                            JLog.i(TAG,"ErrorDialog.dismiss");
                        }
                    }
                })
                .setTitle(context.getResources().getString(R.string.dialog_message))
                .show();
        JLog.i(TAG,"showErrorDialog /n context :{"+context.toString()+"}/n "+"content :{"+content+"}");
    }
    public static void showFriendlyDialog(final Context context, final String content){
        new AlertDialog.Builder(context)
                .setMessage(content)

                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JLog.i(TAG,"ErrorDialog.dismiss");

                    }
                })
                .setTitle(context.getResources().getString(R.string.friendly_dialog_message))
                .show();
        JLog.i(TAG,"showFriendlyDialog /n context :{"+context.toString()+"}/n "+"content :{"+content+"}");
    }
}
