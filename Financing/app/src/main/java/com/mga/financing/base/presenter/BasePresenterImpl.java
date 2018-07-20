package com.mga.financing.base.presenter;

import android.content.Context;

import com.mga.financing.base.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by mga on 2018/4/11 15:26.
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    protected final Context mContext;
    protected WeakReference<V> mView;
    private String TAG = BasePresenterImpl.class.getSimpleName();

    public BasePresenterImpl(Context context) {
        this.mContext=context;
    }

    @Override
    public void attach(BaseView view) {
        mView = new WeakReference<V>((V) view);

    }

    @Override
    public void detach() {
        if (mView != null) {
            mView = null;
            System.gc();
        }
    }

    public V getView() {
        return mView.get();
    }

    public boolean isViewAttach() {
        return getView() != null;
    }


}
