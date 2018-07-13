package com.mga.financing.data.model;

import android.os.Handler;

import com.mga.financing.base.model.BaseModel;
import com.mga.financing.base.model.Callback;

public class UserLoginModel extends BaseModel<String> {
    @Override
    public void execute(final Callback<String> callback) {
        // / 模拟网络请求耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mParams 是从父类得到的请求参数

                switch (mParams.getDescription()) {
                    case "normal":
                        callback.onSuccess("根据参数" + mParams + "的请求网络数据成功");
                        break;
                    case "failure":
                        callback.onFailure("请求失败：参数有误");
                        break;
                    case "error":
                        callback.onError(0,null,null,null);
                        break;
                }
                callback.onComplete();

            }
        }, 500);
    }
//        requestPostAPI(Api.USER_LOGIN, callback);



}



