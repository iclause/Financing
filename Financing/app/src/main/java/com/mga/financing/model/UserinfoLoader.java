package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.UserinfoReq;
import com.mga.financing.bean.response.UserinfoRes;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class UserinfoLoader extends ObjectLoader {
    private UserinfoService mUserinfoService;

    public UserinfoLoader() {
        mUserinfoService = RetrofitServiceManager.getInstance().create(UserinfoService.class);
    }

    /**
     * 登录
     *
     * @param loginReq
     * @return
     */
    public Observable<UserinfoRes> getUserinfo(UserinfoReq userinfoReq) {
        return observe(mUserinfoService.login(userinfoReq))
                .map(new HttpResultFunc<UserinfoRes>());
    }


    public interface UserinfoService {
        @POST(Api.QUERY_USER)
        Observable<BaseResponse<UserinfoRes>> login(@Body UserinfoReq userinfoReq);
    }
}

