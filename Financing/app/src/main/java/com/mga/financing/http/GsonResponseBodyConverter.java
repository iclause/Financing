package com.mga.financing.http;

import android.util.Log;

import com.google.gson.Gson;
import com.mga.financing.base.bean.BaseResponse;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Charles on 2016/3/17.
 */
class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;
    private final String TAG=getClass().getSimpleName();

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
            Log.i(TAG,"<----HTTP----Response---->\n" + response);
            //httpResult 只解析result字段
            BaseResponse httpResult = gson.fromJson(response, BaseResponse.class);
            //
//            if (!"00000000".equals(httpResult.getCode())) {
//                throw new ApiException(Integer.parseInt(httpResult.getCode()));
//            }
            return gson.fromJson(response, type);


    }
}
