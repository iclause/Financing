package com.mga.financing.subscribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mga.financing.http.ApiException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by mga on 2018/8/9 10:32.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private String TAG = getClass().getSimpleName();
    protected SubscriberOnNextListener mSubscriberOnNextListener;
    protected Context context;

    public BaseSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "Throwable exception :" + e.toString());
        if (e instanceof SocketTimeoutException) {
            //系统错误码处理
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            //系统错误码处理
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ApiException) {
            //业务错误码处理，实现类处理
            Log.i(TAG, "业务错误码 ApiException :" + ((ApiException) e).getCode());
            if (mSubscriberOnNextListener != null) {
                mSubscriberOnNextListener.onError((ApiException) e);
            }
        } else {
            //其他错误码处理
            Log.i(TAG, "其他Exception :" + e.toString() + e.getCause() + e.getLocalizedMessage());
            Toast.makeText(context, "error:" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     *
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}
