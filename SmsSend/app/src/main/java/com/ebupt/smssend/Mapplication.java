package com.ebupt.smssend;

import android.app.Application;

import com.ebupt.ebauth.biz.EbAuthDelegate;
import com.ebupt.smssend.util.Jpush;

/**
 * Created by mga on 2018/6/26 19:48.
 */

public class Mapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Jpush.initJpush(this);
        EbAuthDelegate.init(this);
    }
}
