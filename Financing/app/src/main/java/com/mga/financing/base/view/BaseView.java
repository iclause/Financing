package com.mga.financing.base.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by mga on 2017/6/29 18:49.
 * <p>
 * 只负责添加与数据或者某个异步时机相关的ui展示（系统音频操作目前放在activity中）
 */
public interface BaseView<T> {

    /**
     * 显示提示 * @param msg
     */
    void showToast(String msg);
    /**
     * 显示请求错误提示
     */
    void showFailReason(int errcode, Intent intent);


    void showDialog(boolean isshow,String msg);

    void toOtherLayout(Class<Activity> activityClass, Bundle bundle);

    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();

}
