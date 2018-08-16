package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.ChargeReq;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class ChargeLoader extends ObjectLoader {
    private ChargeService mChargeService;

    public ChargeLoader() {
        mChargeService = RetrofitServiceManager.getInstance().create(ChargeService.class);
    }

    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    public Observable<String> charge(ChargeReq chargeReq) {
        return observe(mChargeService.charge(chargeReq))
                .map(new HttpResultFunc<String>());
    }


    public interface ChargeService {
        @POST(Api.DEPOSIT)
        Observable<BaseResponse<String>> charge(@Body ChargeReq chargeReq);
    }
}

