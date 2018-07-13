package com.ebupt.smssend.message;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;

import com.ebupt.jlog1.JLog;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Administrator on 2016/5/4.
 */
public class MJpushReceiver extends BroadcastReceiver {
    public static final String TAG = "MyJpushReceiver";
    private Context mContext;
    private String peername;
    public static PushCallback mPushCallback;
    private LocalBroadcastManager broadcastManager;
    private   static String OFFLINE="mebofflinenotification";

    public void SetOnBind(PushCallback pushcallback) {
        mPushCallback = pushcallback;
        JLog.i(TAG, "SetOnBind:mPushCallback - " + mPushCallback);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        mContext = context;
        JLog.i(TAG, "onReceive - " + intent.getAction());
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String registrationId=bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            JLog.i(TAG, "注册register_id成功" +registrationId );
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            JLog.i(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
            String messageString = bundle.getString(JPushInterface.EXTRA_MESSAGE).trim();
            String extras=bundle.getString(JPushInterface.EXTRA_EXTRA);
            try {
                JSONObject extrajson=new JSONObject(extras);
                String message_type=extrajson.getString("message_type").toString().trim();
                if(mPushCallback!=null){
                    mPushCallback.PushMessageResult(messageString, Integer.parseInt(message_type));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JLog.i(TAG, "msg_content:" + messageString);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 收到通知
            JLog.i(TAG, "收到了通知。通知内容是EXTRA_EXTRA：" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //根据收到的内容判断执行流程
            //1.不操作，打开  后直接跳转主界面 2.未送达短信，打开后跳转短信fragment 3.未送达电话 ，打开后跳转跳转通话记录fragment
            String extraString = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String extratitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            try {
                JSONObject extrajson = new JSONObject(extraString);
                String notify_type = extrajson.get("notify_type").toString().trim();
                JLog.i(TAG, "notify_type:" + notify_type + " extraString:" + extratitle + bundle.getString(JPushInterface.EXTRA_ALERT));
                if (notify_type.equals("1")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 1);
                    }

                } else if (notify_type.equals("2")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 2);
                    }
//                    SmsProcess.getMissSmsWithoutRead(mContext, null, null, null, new SmsProcess.ReceiveSmsCallback() {
//                        @Override
//                        public void receiveSmsResult(List<MessageInfo> list) {
//                            JLog.i(TAG, "receiveSmsResult！" );
//                        }
//
//                        @Override
//                        public void receiveSmsResultWithoutRead() {
//                            mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
//                                    .EXTRA_ALERT),2);
////                            ShortCut.smsHandler.sendEmptyMessage(ConstString.RECIEVESMS_REFRESH);
//                            JLog.i(TAG, "receiveSmsResultWithoutRead！" );
//                        }
//                    });


                } else if (notify_type.equals("3")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 3);
                    }

//
                } else if (notify_type.equals("4")) {
                    if(mPushCallback!=null){
                        JLog.i(TAG, "SetOnBind:mPushCallback4 - " + mPushCallback);
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 4);
                    }
                } else if (notify_type.equals("6")) {
                    if(mPushCallback!=null){
                        JLog.i(TAG, "SetOnBind:mPushCallback6 - " + mPushCallback);
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 6);
                    }
                }else if (notify_type.equals("8")) {
                    if(mPushCallback!=null){
                        JLog.i(TAG, "SetOnBind:mPushCallback8 - " + mPushCallback);
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 8);
                    }
                }else{
                    JLog.i(TAG, "PushOpenDResultk type - " +notify_type);
                    if(mPushCallback!=null){
                        mPushCallback.PushReceiveResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), Integer.parseInt(notify_type));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String extraString = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String extratitle = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            try {
                JSONObject extrajson = new JSONObject(extraString);
                String notify_type = extrajson.get("notify_type").toString().trim();
                JLog.i(TAG, "用户点击打开了通知 - notifyType:" + notify_type);
                if (notify_type.equals("1")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 1);
                    }

                } else if (notify_type.equals("2")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(extratitle, 2);
                    }

                } else if (notify_type.equals("3")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(extratitle, 3);
                    }


//
                } else if (notify_type.equals("4")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 4);
                    }
                }else if (notify_type.equals("8")) {
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), 8);
                    }
                }else{
                    JLog.i(TAG, "PushOpenDResultk type - " +notify_type);
                    if(mPushCallback!=null){
                        mPushCallback.PushOpenDResult(bundle.getString(JPushInterface
                                .EXTRA_ALERT), Integer.parseInt(notify_type));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


//            // 在这里可以自己写代码去定义用户点击后的行为
            //提取参数跳转界面
//            int indexfg = 0;
//            if (ShortCut.jump_type.trim().equals("MISSMSG_LIST")) {
//                indexfg = 2;
//
//            } else if (ShortCut.jump_type.trim().equals("MISSRECORD_LIST")) {
//
//                indexfg = 1;
//            }
//            JLog.i(TAG, "jump_type:" + ShortCut.jump_type + "indexfg:" + indexfg+""+ShortCut.isDestroy+ShortCut.currentTabIndex);
//            if(ShortCut.MainHandler==null) {
//                JLog.i(TAG, "ShortCut.MainHandler==null:");
//            }
//
//
//            if(ShortCut.MainHandler!=null&&ShortCut.currentTabIndex!=4&& !ShortCut.isDestroy) {
//                Message message = ShortCut.MainHandler.obtainMessage();
//                message.what = MainActivity.UPDATE_SMSDETAILFG;
//                Bundle arg = new Bundle();
//
//                arg.putString("number", ShortCut.extratitle);
//                arg.putBoolean("iswrite", false);
//                message.setData(arg);
////                JLog.i(TAG, "sendSmsdetailupdate" + ShortCut.extratitle);
//
//                Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
//                i.putExtra("INDEXFG", indexfg + "");
//                i.putExtra("number", ShortCut.extratitle);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//
//                ShortCut.MainHandler.sendMessage(message);
//                JLog.i(TAG, "sendSmsdetailupdate" + ShortCut.extratitle);
//
//            }else{
//                Intent i = new Intent(context, MainActivity.class);  //自定义打开的界面
//                i.putExtra("INDEXFG", indexfg + "");
//                i.putExtra("number", ShortCut.extratitle);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);

//            }


        } else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {


        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean isCon = JPushInterface.getConnectionState(context);
            try {
                int value1 = Settings.System.getInt(context.getContentResolver(), Settings.System.WIFI_SLEEP_POLICY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * push处理结果的接口
     */
    public interface PushCallback {
        /**
         * push返回方法
         *
         * @param pushInfo
         * @param push_code 说明（1.不操作,打开后直接跳转主界面 2.未送达短信,打开后跳转短信fragment
         *                  3.未送达电话 ,打开后跳转跳转通话记录fragment）4.APP注册未关机
         *                  5、通知用户余额不足 6、通知用户手机号在其他终端登录
         *                  7、通知用户套餐已过期或未订购套餐 8、系统消息
         *
         */
        void PushReceiveResult(String pushInfo, int push_code);

        void PushOpenDResult(String pushInfo, int push_code);

        void PushMessageResult(String pushInfo, int push_code);//自定义通知消息回调


    }
}
