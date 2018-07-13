package com.ebupt.smssend.net;

import android.util.Log;

import com.ebupt.jlog1.JLog;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * 网络接口回调通用解析类
 * Created by ukiy on 15/4/9.
 */
public class MyJsonHttpResponseHandler extends JsonHttpResponseHandler {
    //    private MyApplication app;
    private String TAG=MyJsonHttpResponseHandler.class.getName();

    public MyJsonHttpResponseHandler() {

//        app = MyApplication.getInstance();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result) {
        Log.d(TAG,"threeServerdata(成功)="+ result.toString());
        //模拟并发
//        if(ShortCut.SMSRESPONSEID==0) {
//            ShortCut.SMSRESPONSEID = 1;
//        }else {
//            ShortCut.SMSRESPONSEID = 0;
//        }
//        Json json=new Json();
//        result= InstallJson.installResponseJson(json);




        try {
            String code=result.getString("code");
            if(code.equals("00000000")) {
                resultSuccess(result);
            }else {
                resultFailure(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            finallyDo();
        }


    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        String code="";
        Log.d(TAG, "threeServerdata（失败）=" + statusCode + errorResponse+"throwable:"+throwable.toString());
        try {
            if(statusCode==0){
                if(throwable.toString().contains("java.net.SocketTimeoutException")){
                    JLog.i(TAG, "网络请求超时，请重试！SocketTimeoutException");
                    Log.d(TAG,"SocketTimeoutException");
                    timeOut();

                }else if(throwable.toString().contains("java.net.ConnectException")){
                    JLog.i(TAG, "网络请求超时，请重试！ConnectException");
                    Log.d(TAG,"ConnectException");
                    NotNet();

                }else{
//
                    JLog.i(TAG, "网络请求超时，请重试！otherException ");
                }
            }
            if(errorResponse!=null){

                code=errorResponse.getString("code");
                if(code.equals("")){
                    JLog.i(TAG, "网络错误 ");
                }else{

                    JLog.i(TAG,  code+":" +  ErrorDescription.GetDescription("" + code));
                    resultFailure(errorResponse);
                }


            }
            finallyDo();
        }catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "threeServerdata（失败）=" + statusCode + errorResponse);
            finallyDo();
        }




    }



    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        JLog.d(TAG, "threeServerdata（）responseString=" + statusCode + responseString + "throwable:" + throwable
                .toString());
        if(statusCode==200){
            if(responseString.trim().equals("file upload success")){
                JLog.d(TAG, "responseString.trim().equals(file upload success)");
                JSONObject result = new JSONObject();
                try {
                    result.put("code", "00000000");
                    resultSuccess(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return;
            }
            JLog.d(TAG, "!!!!responseString.trim().equals(file upload)");
        }
        finallyDo();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        JLog.d(TAG, "threeServerdata（失败）errorResponse=" + statusCode + errorResponse + "throwable:" + throwable.toString());
        finallyDo();
    }
    public void timeOut(){

    }
    public void NotNet(){

    }


    public void resultSuccess(JSONObject result) throws JSONException {
//        Toast.makeText(app, result.getString("msg"), Toast.LENGTH_SHORT).show();
    }

    /**
     * 业务性错误，非“00000000”
     * @param result
     * @throws JSONException
     */
    public void resultFailure(JSONObject result) throws JSONException {

    }

    public void finallyDo() {
        Log.d(TAG,"==finallyDo===");
    }



}
