package com.mga.financing.http;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 拦截器
 * <p>
 * 向请求头里添加公共参数
 * Created by zhouwei on 16/11/10.
 */
public class HttpCommonInterceptor implements Interceptor {
    private static final String TAG ="HttpCommonInterceptor" ;
    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpCommonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Log.d("HttpCommonInterceptor", "add common params");
//        Request oldRequest = chain.request();
//        // 添加新的参数，添加到url 中
//     /* HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()                .newBuilder()
//         .scheme(oldRequest.url().scheme())
//             .host(oldRequest.url().host());*/
//        // 新的请求
//        Request.Builder requestBuilder = oldRequest.newBuilder();
//        requestBuilder.method(oldRequest.method(),
//                oldRequest.body());
//
//        //添加公共参数,添加到header中
//        if (mHeaderParamsMap.size() > 0) {
//            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
//                requestBuilder.header(params.getKey(), params.getValue());
//            }
//        }
//        Request newRequest = requestBuilder.build();
//        long startTime = System.currentTimeMillis();
//        okhttp3.Response response = chain.proceed(newRequest);
//        long endTime = System.currentTimeMillis();
//        long duration=endTime-startTime;
//        okhttp3.MediaType mediaType = response.body().contentType();
//        String content = response.body().string();
//        Log.i(TAG,"╔═════════════════════════════════════════════════════Start═══════════════════════════════════════════════════════════");
//        Log.i(TAG, "║ "+newRequest.toString());
//        String method=newRequest.method();
//        if("POST".equals(method)){
//            StringBuilder sb = new StringBuilder();
//            if (newRequest.body() instanceof FormBody) {
//                FormBody body = (FormBody) newRequest.body();
//                for (int i = 0; i < body.size(); i++) {
//                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
//                }
//                sb.delete(sb.length() - 1, sb.length());
//                Log.i(TAG, "║ RequestParams:{"+sb.toString()+"}");
//            }
//        }
//        Log.i(TAG, "║ Response:" + content);
//        Log.i(TAG,"╚═════════════════════════════════════════════════════End:"+duration+"毫秒═════════════════════════════════════════════════════");
//
//        return chain.proceed(newRequest);

        Log.d("HttpCommonInterceptor", "add common params");
        Request oldRequest = chain.request();
        // 添加新的参数，添加到url 中
     /* HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()                .newBuilder()
         .scheme(oldRequest.url().scheme())
             .host(oldRequest.url().host());*/
        // 新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(),
                oldRequest.body());

        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }
    }
}
