package com.mga.financing.mvp.order;

import android.content.Context;
import android.util.Log;

import com.mga.financing.R;
import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.Token;
import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.mvp.main.MainActivity;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/6/20 20:03.
 */

public class BuyPresenter extends BasePresenterImpl<BuyOrderContact.View> implements BuyOrderContact.Presenter {
    private String TAG = getClass().getSimpleName();

    public BuyPresenter(Context context) {
        super(context);
    }


    @Override
    public void buy() {
        if (isViewAttach()) {
            getView().showDialog(true, getView().getContext().getResources().getString(R.string.loading));
        }
        BaseNet baseNet = new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA, baseNet, new Callback() {
            @Override
            public void onSuccess(Object data) {
                //tologinsecond
                Log.i(TAG, "onSuccess");
                if (isViewAttach()) {

                    getView().toOtherLayout(MainActivity.class, null);
                    getView().showToast("购买成功");

                }
            }

            @Override
            public void onFailure(Object data) {
                //toregist

            }

            @Override
            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
                if (isViewAttach()) {
                    getView().showDialog(false, null);
                }
            }
        });
    }
}
