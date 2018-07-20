package com.mga.financing.base.presenter;

import com.mga.financing.base.view.BaseView;

/**
 * Created by mga on 2017/6/29 18:49.
 */
public interface BasePresenter<V extends BaseView> {

    /**
     * 在view生命周期oncreate中绑定view
     * 防止内存泄漏
     * @param view
     */
    void attach(V view);
    /**
     * 在view生命周期ondestroy中绑定view
     * 防止内存泄漏
     */
    void detach();

}
