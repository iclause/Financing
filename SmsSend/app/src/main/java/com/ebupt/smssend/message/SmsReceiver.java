package com.ebupt.smssend.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.text.format.Time;

import com.ebupt.jlog1.JLog;

public class SmsReceiver extends BroadcastReceiver {
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsReceiver";
    private String strContent = "";

    public SmsReceiver() {
        JLog.i(TAG, "new SmsReceiver");
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        JLog.i(TAG, "onReceive broadcastreciever");
        if (SMS_RECEIVED.equals(intent.getAction())) {
            Object[] objs = (Object[]) intent.getExtras().get("pdus");
            for (Object obj : objs) {
                byte[] pdu = (byte[]) obj;
                SmsMessage sms = SmsMessage.createFromPdu(pdu);
                // 短信的内容
                String message = sms.getMessageBody();
                JLog.i(TAG, "message  " + message);
                // 短息的手机号。。+86开头？
                String from = sms.getOriginatingAddress();
                JLog.i(TAG, "from  " + from);
                Time time = new Time();
                time.set(sms.getTimestampMillis());
                String time2 = time.format3339(true);
                JLog.i(TAG, from + " " + message + " " + time2);
                strContent = from + " " + message;
                // TODO: 2018/6/26 上报

            }
        }


    }
}