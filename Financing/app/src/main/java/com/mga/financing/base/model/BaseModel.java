package com.mga.financing.base.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.net.MyJsonHttpResponseHandler;
import com.mga.financing.net.NetEngine;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/4/11 18:20.
 */

public abstract class BaseModel<T> {
    private static final String TAG =BaseModel.class.getSimpleName() ;
    //数据请求参数
    protected BaseNet mParams;
    protected Context mContext;

    /**
     * 设置数据请求参数
     *
     * @param param 参数数组
     */
    public BaseModel params(Context context,BaseNet param) {
        mParams = param;
        mContext=context;
        return this;
    }

    // 添加Callback并执行数据请求
    // 具体的数据请求由子类实现
    public abstract void execute(Callback<T> callback);

    // 执行Get网络请求，此类看需求由自己选择写与不写
    protected void requestGetAPI(String url, Callback<T> callback) {
        //这里写具体的网络请求
    }

    // 执行Post网络请求，此类看需求由自己选择写与不写
    protected void requestPostAPI(String url, final Callback<T> callback) {
        //这里写具体的网络请求
        final Gson gson=new Gson();
        NetEngine.submitPostTask(mContext,url,mParams,new MyJsonHttpResponseHandler(){

            @Override
            public void resultSuccess(JSONObject result) throws JSONException {
                super.resultSuccess(result);
                Log.i(TAG,"resultSuccess(){ result :+"+result.toString()+"}");
                callback.onSuccess((T) result);
            }

            @Override
            public void resultFailure(JSONObject result) throws JSONException {
                super.resultFailure(result);
                Log.i(TAG,"resultFailure(){ result :+"+result.toString()+"}");
                callback.onFailure((T) result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.i(TAG,"onFailure() {statusCode :"+statusCode+";  responseString"+responseString+"; throwable"+throwable.toString()+"}");
                callback.onError( statusCode,  headers,  responseString,  throwable);
            }

            @Override
            public void finallyDo() {
                super.finallyDo();
                Log.i(TAG,"finallyDo(){}");

                callback.onComplete();
            }
        });
    }


}
