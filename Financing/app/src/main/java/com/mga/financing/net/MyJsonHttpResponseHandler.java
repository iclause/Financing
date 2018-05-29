package com.mga.financing.net;

import android.text.TextUtils;

import com.ebupt.jlog.JLog;
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
    private String headApi="";

    public MyJsonHttpResponseHandler() {
        this("");
//        app = MyApplication.getInstance();
    }

    public MyJsonHttpResponseHandler(String api) {
        headApi=api;
//        app = MyApplication.getInstance();
    }

    public void setHeadApi(String api){
        headApi=api;
    }

    public String subHeadApi(){
        if(!TextUtils.isEmpty(headApi)){
            return headApi.substring(headApi.lastIndexOf("/")+1,headApi.length());
        }
        return headApi;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result) {
        JLog.i(TAG,"* rqandrp *"+subHeadApi()+"结果：\n"+"threeServerdata(成功)="+ result.toString());
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
        JLog.i(TAG, "* rqandrp *"+subHeadApi()+"结果：\n"+"threeServerdata（失败）=" + statusCode + errorResponse+"throwable:"+throwable.toString());
        try {
            if(statusCode==0){
                if(throwable.toString().contains("java.net.SocketTimeoutException")){
//                    Toast.makeText(Utils.getContext(), "网络请求超时，请重试！" , Toast.LENGTH_SHORT).show();
                    JLog.d(TAG,"SocketTimeoutException");
                    timeOut();

                }else if(throwable.toString().contains("java.net.ConnectException")){
//                    Toast.makeText(Utils.getContext(), "网络连接失败，请重试！" , Toast.LENGTH_SHORT).show();
                    JLog.d(TAG,"ConnectException");
                    NotNet();

                }else{
//
//                    Toast.makeText(Utils.getContext(), "网络连接异常" , Toast.LENGTH_SHORT).show();
                }
            }
            if(errorResponse!=null){

                code=errorResponse.getString("code");
                if(code.equals("")){
//                    Toast.makeText(Utils.getContext(), "网络错误：" + statusCode +  errorResponse, Toast.LENGTH_SHORT).show();
                }else{

//                    Toast.makeText(Utils.getContext(),  code+":" +  ErrorDescription.GetDescription("" + code), Toast.LENGTH_SHORT).show();
                    resultFailure(errorResponse);
                }


            }
            finallyDo();
        }catch (Exception e) {
            e.printStackTrace();
            JLog.d(TAG, "* rqandrp *"+subHeadApi()+"结果：\n"+"threeServerdata（失败）=" + statusCode + errorResponse);
            finallyDo();
        }




    }



    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        JLog.i(TAG, "* rqandrp *"+subHeadApi()+"结果：\n"+"threeServerdata（）responseString=" + statusCode + responseString + "throwable:" + throwable
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
        JLog.i(TAG, "* rqandrp *"+subHeadApi()+"结果：\n"+"threeServerdata（失败）errorResponse=" + statusCode + errorResponse + "throwable:" + throwable.toString());
        finallyDo();
    }
    public void timeOut(){
//        JLog.i(TAG,"* rqandrp *"+subHeadApi()+"结果：\n"+"网络是否用ping通："+NetUtil.isAvailableByPing());
    }
    public void NotNet(){
//        JLog.i(TAG,"* rqandrp *"+subHeadApi()+"结果：\n"+"网络是否用ping通："+NetUtil.isAvailableByPing());
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
        JLog.d(TAG,"* rqandrp *"+subHeadApi()+"结果：\n"+"==finallyDo===");
    }



}
