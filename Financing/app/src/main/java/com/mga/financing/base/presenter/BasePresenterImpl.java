package com.mga.financing.base.presenter;

import android.util.Log;

import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.DataModel;
import com.mga.financing.base.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * Created by mga on 2018/4/11 15:26.
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    protected WeakReference<V> mView;
    private String TAG = BasePresenterImpl.class.getSimpleName();


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

    @Override
    public void getData(String token, BaseNet baseNet, Callback callback) {
        if (baseNet == null) {
            Log.i(TAG, "basenet==null");
        }
        if (getView() == null) {
            Log.i(TAG, "getView==null");
        }
        DataModel.request(token)
                .params(getView().getContext(), baseNet)
                .execute(callback);
    }
}
