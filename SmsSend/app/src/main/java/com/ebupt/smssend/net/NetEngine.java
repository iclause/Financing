package com.ebupt.smssend.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ebupt.jlog1.JLog;
import com.ebupt.smssend.bean.BaseNet;
import com.ebupt.smssend.bean.BaseRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.json.JSONObject;

import java.net.URI;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.RedirectHandler;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.protocol.HttpContext;


/**
 * 网络接口调用类
 * Created by Ljk on 15/03/20.
 */
public class NetEngine {
    public static final String TAG = NetEngine.class.getSimpleName();
    private static AsyncHttpClient client = new AsyncHttpClient(true,8080,8443);
//    private static AsyncHttpClient client = new AsyncHttpClient();
    // 待部署现网后，将修改为现网访问地址
    //内网地址
//    public final static String BASE_URL = "http://10.1.62.23:8080";
    //公网地址
    public final static String BASE_URL = "http://218.249.60.71:8080";
    //    public final static String GET_URL = "https://api.douban.com/v2/user/abei";
    public final static String GET_URL = "http://gc.ditu.aliyun.com/geocoding?a=%E8%8B%8F%E5%B7%9E%E5%B8%82";

    private static RequestHandle downloadHandler;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor edit;
    private static ProgressDialog mProgressDialog;


    public static void downloadFile(FileAsyncHttpResponseHandler handler, String url) {
        downloadHandler = client.get(url, handler);
    }

    public static void cancelDownloadFile() {
        if (downloadHandler == null) return;
        downloadHandler.cancel(true);
    }

    public static void submitPostTask(Context c, String uri, JSONObject jsonObject, JsonHttpResponseHandler handler) {
        HttpEntity entity = null;
        try {
            entity = new StringEntity(jsonObject.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        client.post(c, uri, entity, "application/json;charset=UTF-8", handler);
//        client.get(c, GET_URL, handler);
    }

    public static void submitPostSimulation(AsyncHttpResponseHandler handler){

        AsyncHttpClient httpClient=new AsyncHttpClient();
        RedirectHandler redirectHandler = new RedirectHandler() {
            @Override
            public boolean isRedirectRequested(cz.msebera.android.httpclient.HttpResponse response, HttpContext context) {
                return false;
            }

            @Override
            public URI getLocationURI(cz.msebera.android.httpclient.HttpResponse response, HttpContext context) throws cz.msebera.android.httpclient.ProtocolException {
                return null;
            }


        };
        httpClient.setRedirectHandler(redirectHandler);//关闭重定向
        httpClient.get("https://www.baidu.com",handler);

    }

    public static void submitPostTask(Context c, String uri, BaseNet baseNet, JsonHttpResponseHandler handler) {
        HttpEntity entity = null;
        try {
            JLog.i(TAG,"网络请求："+uri);
             entity = InstallJson.installJsonEntity(baseNet);

        } catch (Exception e) {
            e.printStackTrace();
        }


        client.post(c, uri, entity, "application/json;charset=UTF-8", handler);
//        client.get(c, GET_URL, handler);
    }
    public static void submitPostTaskHeader(Context c, String uri, BaseNet baseNet, JsonHttpResponseHandler handler) {
        Log.d(TAG, "thread==" + Thread.currentThread().getName());
        if(Api.UPDATELOCATION.equals(uri)){
            Log.i(TAG,"client.setMaxRetriesAndTimeout(1,1001);");
            client.setMaxRetriesAndTimeout(1,1600);
        }else{
            client.setMaxRetriesAndTimeout(2,3200);
        }
        HttpEntity entity = InstallJson.installJsonEntitynoBody(uri,baseNet);
        client.addHeader("Authorization", InstallJson.GetHeader());
        client.post(c, uri,
                entity, "application/json;charset=UTF-8", handler);
    }

    /**
     * 支持发送BaseRequest类型参数
     * @param c
     * @param uri
     * @param baseRequest
     * @param handler
     */
    public static void submitPostTaskHeader(Context c, String uri, BaseRequest baseRequest, JsonHttpResponseHandler handler) {
        Log.d(TAG, "thread==" + Thread.currentThread().getName());
        if(Api.UPDATELOCATION.equals(uri)){
            Log.i(TAG,"client.setMaxRetriesAndTimeout(1,1001);");
            client.setMaxRetriesAndTimeout(1,1600);
        }else{
            client.setMaxRetriesAndTimeout(3,3200);
        }
        HttpEntity entity = InstallJson.installJsonEntitynoBody(uri,baseRequest);
        client.addHeader("Authorization", InstallJson.GetHeader());
        client.post(c, uri,
                entity, "application/json;charset=UTF-8", handler);
    }
    public static void submitPostTaskHeader(Context c, String uri, Long timestamp, BaseNet baseNet, JsonHttpResponseHandler handler) {
        Log.d(TAG, "thread==" + Thread.currentThread().getName());
        if(Api.UPDATELOCATION.equals(uri)){
            Log.i(TAG,"client.setMaxRetriesAndTimeout(1,1001);");
            client.setMaxRetriesAndTimeout(1,1600);
        }else{
            client.setMaxRetriesAndTimeout(2,3200);
        }
        HttpEntity entity = InstallJson.installJsonEntitynoBody(uri,baseNet);
        client.addHeader("Authorization", InstallJson.GetHeader(timestamp));
        client.post(c, uri,
                entity, "application/json;charset=UTF-8", handler);
    }
    public static void submitGetTask(Context c, String uri, String[] params, JsonHttpResponseHandler handler) {
        client.get(c, GET_URL, handler);
    }

}
