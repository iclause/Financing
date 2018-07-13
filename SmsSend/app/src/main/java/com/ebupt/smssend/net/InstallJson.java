package com.ebupt.smssend.net;


import com.ebupt.jlog1.JLog;
import com.ebupt.smssend.bean.BaseNet;
import com.ebupt.smssend.bean.BaseRequest;
import com.google.gson.Gson;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;


/**
 * 网络数据构建类
 * Created by Administrator on 2016/6/3.
 */
public class InstallJson {
    private static final String TAG = InstallJson.class.toString();



    public static HttpEntity installJsonEntitynoBody(String uri, BaseNet json) {
        Gson gson = new Gson();

        String jsonStr = gson.toJson(json);
        JLog.d(TAG, uri.substring(uri.lastIndexOf("/")+1,uri.length())+"参数：\n"+jsonStr);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(jsonStr, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 将发送请求体转换成entity类型
     * @param uri
     * @param json
     * @return
     */

    public static HttpEntity installJsonEntitynoBody(String uri, BaseRequest json) {
        Gson gson = new Gson();

        String jsonStr = gson.toJson(json);
        JLog.d(TAG, uri.substring(uri.lastIndexOf("/") + 1, uri.length()) + "参数：\n" + jsonStr);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(jsonStr, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
    //组装发送参数json实体类
    public static HttpEntity installJsonEntity(BaseNet baseNet) {
        Gson gson = new Gson();
//header
        baseNet.setAppkey(Api.appkey);
        Long timestamp= System.currentTimeMillis();
        baseNet.setTimestamp(String.valueOf(timestamp/1000));
        baseNet.setSign(Api.getSign(timestamp));
        String jsonStr = gson.toJson(baseNet);
        JLog.d(TAG, jsonStr);
        HttpEntity entity = null;
        try {
            entity = new StringEntity(jsonStr,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public static String GetHeader(){
        Long timestamp= System.currentTimeMillis();
        String header = " WIFICALLINGAUTH "+"appkey="+ Api.appkey+","+"timestamp="+ String.valueOf(timestamp/1000)+","+"sign="+Api.getSign(timestamp)+"";
        return header;
    }
    public static String GetHeader(Long timestamp){
        Long mTimestamp;
        if(timestamp!=0){
            mTimestamp =timestamp;
        }else{
            mTimestamp = System.currentTimeMillis();;
        }
        String header = " WIFICALLINGAUTH "+"appkey="+ Api.appkey+","+"timestamp="+ String.valueOf(mTimestamp/1000)+","+"sign="+Api.getSign(timestamp)+"";
        return header;
    }










}
