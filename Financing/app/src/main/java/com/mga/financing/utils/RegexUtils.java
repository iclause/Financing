package com.mga.financing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mga on 2018/5/30 16:03.
 */

public class RegexUtils {

    /**
     * 验证是否是电话号码
     *
     * @param str
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
    public static boolean isPhoneNumber(String str) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证是否为有效身份证件
     * @param str
     * @return
     */
    public static boolean isIdCard(String str) {
        Pattern p1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"); //15位
        Pattern p2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$"); //18位
        Matcher m1 = p1.matcher(str);
        Matcher m2 = p2.matcher(str);
        return m1.matches()||m2.matches();
    }

    /**
     * 验证是否为有效银行卡
     * @param str
     * @return
     */
    public static boolean isBankCard(String str) {
        Pattern p = Pattern.compile("^([1-9]{1})(\\d{14}|\\d{18})$");
        Matcher m = p.matcher(str);
        return m.matches();
    }


}
