package com.mga.financing.http;

/**
 * Created by liukun on 16/3/10.
 */
public class ApiException extends RuntimeException {
    public  int code;
    private String TAG = getClass().getSimpleName();
    public ApiException(int resultCode) {
        this.code=resultCode;
    }
    public int getCode(){
        return code;
    }


}

