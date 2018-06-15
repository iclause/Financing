package com.mga.financing.mvp.charge;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.R;
import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.Token;
import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.mvp.regist.RegistActivity;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/6/15 15:55.
 */

public class ChargePresenter extends BasePresenterImpl<ChargeContact.View> implements ChargeContact.Presenter {

    private final Context mContext;
    private String TAG=getClass().getSimpleName();

    public ChargePresenter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public void submit() {
        if(isViewAttach()) {
            getView().showDialog(true,getView().getContext().getResources().getString(R.string.loading));
        }
        BaseNet baseNet=new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA,baseNet, new Callback() {
            @Override
            public void onSuccess(Object data) {
                //tologinsecond
                Log.i(TAG,"onSuccess");
                if(isViewAttach()) {
                getView().showPopDialog();

                }
            }

            @Override
            public void onFailure(Object data) {
                //toregist
                if(isViewAttach()) {
                    getView().toOtherLayout(RegistActivity.class,new Bundle());
                }
            }

            @Override
            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete");
                if(isViewAttach()) {
                    getView().showDialog(false,null);
                }
            }
        });
    }

    @Override
    public void submitPassword() {
        if(isViewAttach()) {
            getView().showDialog(true,getView().getContext().getResources().getString(R.string.loading));
        }
        BaseNet baseNet=new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA,baseNet, new Callback() {
            @Override
            public void onSuccess(Object data) {
                //tologinsecond
                Log.i(TAG,"onSuccess");
                if(isViewAttach()) {
                    getView().dismissPopDialog();

                }
            }

            @Override
            public void onFailure(Object data) {
                //toregist
                if(isViewAttach()) {
                    getView().toOtherLayout(RegistActivity.class,new Bundle());
                }
            }

            @Override
            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG,"onComplete");
                if(isViewAttach()) {
                    getView().showDialog(false,null);
                }
            }
        });
    }
}
