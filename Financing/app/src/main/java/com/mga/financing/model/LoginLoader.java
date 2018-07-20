package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.LoginReq;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class LoginLoader extends ObjectLoader {
    private LoginService mLoginService;

    public LoginLoader() {
        mLoginService = RetrofitServiceManager.getInstance().create(LoginService.class);
    }

    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    public Observable<String> login(LoginReq loginReq) {
        return observe(mLoginService.login(loginReq))
                .map(new HttpResultFunc<String>());
    }


    public interface LoginService {
        @POST(Api.LOGIN)
        Observable<BaseResponse<String>> login(@Body LoginReq loginReq);
    }
}

