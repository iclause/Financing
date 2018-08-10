package com.mga.financing.subscribers;

import android.content.Context;

/**
 * 不带进度框的网络请求
 * Created by mga on 2018/8/9 10:55.
 */

public class NormalSubscriber extends BaseSubscriber {
    public NormalSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        super(mSubscriberOnNextListener, context);
    }

}
