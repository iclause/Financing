package com.mga.financing.http;

import com.mga.financing.base.bean.BaseResponse;

import rx.functions.Func1;

public class HttpResultFunc<T> implements Func1<BaseResponse<T>, T> {

        @Override
        public T call(BaseResponse<T> httpResult) {
            if(!"00000000".equals(httpResult.getCode())){
                throw new ApiException(Integer.parseInt(httpResult.getCode()));
            }
            return httpResult.getData();
        }
    }