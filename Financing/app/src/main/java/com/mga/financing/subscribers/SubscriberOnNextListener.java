package com.mga.financing.subscribers;

import com.mga.financing.http.ApiException;

/**
 * Created by mga on 18/7/23.
 * 只处理业务码（业务成功、失败）
 * 系统错误码交给ProgressSubscriber类中onerror方法处理
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);//业务码成功

    void onError(ApiException e);//业务码错误


}
