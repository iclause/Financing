package com.ebupt.smssend.util;

import android.content.Context;
import android.util.Log;

import com.ebupt.jlog1.JLog;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by dangelo on 17/4/14.
 */
public class Jpush {
    public static Set<String> tags=new HashSet<String>();
    private static String TAG=Jpush.class.getSimpleName();
    public static void initJpush(Context context) {
        Log.d(TAG,"initjpush");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(context);
        boolean isCon= JPushInterface.getConnectionState(context);
        JLog.i(TAG, isCon + "");
//        注册registration_id=170976fa8a8220f42d4
        String registration_id= JPushInterface.getRegistrationID(context);
        JLog.i(TAG,"注册mid_registration_id"+registration_id);
      
        tags.add("htcone");

        JPushInterface.setAlias(context, "ebupt", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                JLog.i(TAG,"setAlias"+ "huwei" + s + "");
            }
        });

//        设置通知别名alias与标签tags

        JPushInterface.setTags(context, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                JLog.i(TAG,"resp"+ String.valueOf(i));
                if (i == 0) {
                    JLog.i("tags", "设置tags成功");
                }
            }
        });
    }

}
