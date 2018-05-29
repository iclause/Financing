package com.mga.financing.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mga on 2018/4/23 17:18.
 * 校验类
 */

public class ECCUtils {

    /**
     * 验证是否是电话号码
     *
     * @param num
     * @return 最常用的几个正则匹配:
     * <p/>
     * 用户名：3-15任意字符组合
     * usernameRegex = /^\w{3,15}$/;
     * 密码：6-12任意字符组合
     * passwordRegex = /^\w{6,12}$/;
     * 邮箱：自己实现验证规则
     * emailRegex = /^\w+@\w+(\.\w+)+$/;
     * 真实姓名：必须是2-5中文
     * <p/>
     * realNameRegex = /^[\u4e00-\u9fa5]{2,5}$/;
     */
    public static boolean isPhoneNumber(String num) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(num);
        return m.matches();

    }

    /**
     * 验证密码 以字母开头，长度在8~18之间，只能包含字符、数字
     *
     * @param password
     */
    public static boolean isPassword(String password) {
        String passwordRegex ="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,18}$";
        boolean result = Pattern.matches(passwordRegex, password);
        return result;
    }
    /**
     * 验证是否包含特殊字符
     *
     * @param num
     * @return 最常用的几个正则匹配:
     */
    public static boolean isContainspecialChar(String num) {
        Pattern p = Pattern.compile("[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t");
        Matcher m = p.matcher(num);
        return m.find();

    }
    /**
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cellphone);
        return m.matches();
    }



    /**
     * 是否是验证码
     */
    public static boolean isVertificationCode(String code) {
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(code);
        return m.matches();
    }


    /** 判断某个界面是否在前台
     *
     * @param activity 要判断的Activity
     * @return 是否在前台显示
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className))
            return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName()))
                return true;
        }
        return false;
    }

    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean isEmail(String email) {
        String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
        boolean result = Pattern.matches(emailPattern, email);
        return result;
    }


}
