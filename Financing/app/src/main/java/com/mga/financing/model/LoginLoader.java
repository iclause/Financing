package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.LoginRequest;
import com.mga.financing.http.Api;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

public class LoginLoader extends ObjectLoader {
    private LoginService mLoginService;

    public LoginLoader() {
        mLoginService = RetrofitServiceManager.getInstance().create(LoginService.class);
    }

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    public Observable<String> login(LoginRequest loginRequest) {
        return observe(mLoginService.login(loginRequest))
                .map(new Func1<BaseResponse<String>, String>() {
                    @Override
                    public String call(BaseResponse<String> stringBaseResponse) {
                        //可以处理对应的逻辑后在返回
                        return stringBaseResponse.getCode();
                    }
                });
    }


    public interface LoginService {
        @POST(Api.LOGIN)
        Observable<BaseResponse<String>> login(@Body LoginRequest loginRequest);
    }
}

