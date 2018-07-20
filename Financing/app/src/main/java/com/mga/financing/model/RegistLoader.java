package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.RegistReq;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
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

    public Observable<Integer> regist(RegistReq registReq) {
        return observe(mRegistService.regist(registReq))
                .map(new Func1<BaseResponse<String>, Integer>() {
                    @Override
                    public Integer call(BaseResponse<String> s) {
                        //可以处理对应的逻辑后在返回
                        return Integer.parseInt(s.getCode());
                    }
                });
    }

    public Observable<Integer> isRegist(RegistReq registReq) {
//        return observe(mRegistService.isRegist(registReq))
//                .map(new Func1<BaseResponse<String>, Integer>() {
//                    @Override
//                    public Integer call(BaseResponse<String> s) {
//                        //可以处理对应的逻辑后在返回
//                        return Integer.parseInt(s.getCode());
//                    }
//                });
        return observe(mRegistService.isRegist(registReq))
                .map(new HttpResultFunc<Integer>());
    }

    public interface RegistService {
        @POST(Api.REGIST)
        Observable<BaseResponse<String>> regist(@Body RegistReq registReq);

        @POST(Api.ISREGIST)
        Observable<BaseResponse<Integer>> isRegist(@Body RegistReq registReq);

    }
}
