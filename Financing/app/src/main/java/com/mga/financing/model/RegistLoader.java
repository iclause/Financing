package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.RegistRequest;
import com.mga.financing.http.Api;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by mga on 2018/7/11 14:39.
 */

public class RegistLoader extends ObjectLoader {
    private RegistService mRegistService;

    public RegistLoader() {
        mRegistService = RetrofitServiceManager.getInstance().create(RegistService.class);
    }

    public Observable<String> regist(RegistRequest registRequest) {
        return observe(mRegistService.regist(registRequest))
                .map(new Func1<BaseResponse<String>, String>() {
                    @Override
                    public String call(BaseResponse<String> s) {
                        //可以处理对应的逻辑后在返回
                        return s.getCode();
                    }
                });
    }

    public interface RegistService {
        @POST(Api.REGIST)
        Observable<BaseResponse<String>> regist(@Body RegistRequest registRequest);

    }
}
