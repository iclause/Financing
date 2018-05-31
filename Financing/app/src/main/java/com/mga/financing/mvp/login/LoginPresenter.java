package com.mga.financing.mvp.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.R;
import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.Token;
import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.main.MainActivity;
import com.mga.financing.mvp.regist.RegistActivity;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.UserInfoManager;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/4/12 11:22.
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.View> implements LoginContact.Presenter {


    private static final String TAG =LoginPresenter.class.getSimpleName() ;
    private Context mContext;

    public LoginPresenter(Context context) {
        super(context);
        this.mContext=context;
    }


    @Override
    public void checkIsRegist(String account) {


        // TODO: 2018/4/17 网络请求
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
                    Bundle bundle=new Bundle();
                    bundle.putString(BundleKeyConstant.PHONENUMBER,getView().getPhoneNumber());
                    getView().toOtherLayout(LoginPasswordActivity.class,bundle);
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
    public void login(final String account, final String password) {
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
                UserInfoManager.saveAccount(mContext,account);
                UserInfoManager.savePassword(mContext,account,password);
                UserInfoManager.setIsAutoLogin(mContext,true);
                if(isViewAttach()) {
                    Bundle bundle=new Bundle();
                    getView().toOtherLayout(MainActivity.class,bundle);
                    AppManager.finishAllActivity();

                }
            }

            @Override
            public void onFailure(Object data) {
                //toregist
                UserInfoManager.clearUserLoginInfo(mContext,account);


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
    public void vfc(String account) {
        if(isViewAttach()) {
            getView().showDialog(true,getView().getContext().getResources().getString(R.string.loading));
        }
        BaseNet baseNet=new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA,baseNet, new Callback() {
            @Override
            public void onSuccess(Object data) {
                //tologinsecond
                Log.i(TAG,"vfconSuccess");
                if(isViewAttach()) {
                    Log.i(TAG,"isViewAttach");
                    Bundle bundle=new Bundle();
                    getView().toOtherLayout(MainActivity.class,bundle);
                    AppManager.finishAllActivity();
                }else{
                    Log.i(TAG,"isViewnotAttach");
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
                Log.i(TAG,"onComplete");
                if(isViewAttach()) {
                    getView().showDialog(false,null);
                }
            }
        });
    }

    @Override
    public void regist(String account) {
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
                    getView().toOtherLayout(MainActivity.class,new Bundle());
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
                Log.i(TAG,"onComplete");
                if(isViewAttach()) {
                    getView().showDialog(false,null);
                }
            }
        });
    }

    @Override
    public void audioVfc(String account) {
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
                    getView().toOtherLayout(MainActivity.class,new Bundle());
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
                Log.i(TAG,"onComplete");
                if(isViewAttach()) {
                    getView().showDialog(false,null);
                }
            }
        });
    }

}
