package com.ebupt.smssend;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView contentTv;
    private BroadcastReceiver smsReceiver;
    private IntentFilter filter2;
    private EditText et;
    private String strContent;
    private String patternCoder = "(?<!--\\d)\\d{6}(?!\\d)";
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            contentTv.setText(strContent);
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentTv=(TextView)findViewById(R.id.content);

//        filter2 = new IntentFilter();
//        filter2.addAction("android.provider.Telephony.SMS_RECEIVED");
//        filter2.setPriority(Integer.MAX_VALUE);
//        smsReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.i("onReceive", "broadcastreciever");
//                Object[] objs = (Object[]) intent.getExtras().get("pdus");
//                for (Object obj : objs) {
//                    byte[] pdu = (byte[]) obj;
//                    SmsMessage sms = SmsMessage.createFromPdu(pdu);
//                    // 短信的内容
//                    String message = sms.getMessageBody();
//                    Log.i("logo", "message  " + message);
//                    // 短息的手机号。。+86开头？
//                    String from = sms.getOriginatingAddress();
//                    Log.i("logo", "from  " + from);
//                    Time time = new Time();
//                    time.set(sms.getTimestampMillis());
//                    String time2 = time.format3339(true);
//                    Log.i("logo", from + " " + message + " " + time2);
//                    strContent = from + " " + message;
//                    handler.sendEmptyMessage(1);
//                    if (!TextUtils.isEmpty(from)) {
//                        String code = patternCode(message);
//                        if (!TextUtils.isEmpty(code)) {
//                            strContent = code;
//                            handler.sendEmptyMessage(1);
//                        }
//                    }
//                }
//            }
//        };
//        registerReceiver(smsReceiver, filter2);

    }

    /**
     * 匹配短信中间的6个数字（验证码等）
     *
     * @param patternContent
     * @return
     */
    private String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(smsReceiver);

    }
}
