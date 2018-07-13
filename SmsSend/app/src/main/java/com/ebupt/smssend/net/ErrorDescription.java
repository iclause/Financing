package com.ebupt.smssend.net;

/**
 * 错误码解析类
 * Created by dangelo on 17/4/20.
 */
public class ErrorDescription {
    public static String GetDescription(String code) {
        String Description = "";
        if (code.equals("00040001")) {
            Description = "发送失败";
        }  else if (code.equals("00040002")) {
            Description = "订购失败-用户已订购";
        } else if (code.equals("00040003")) {
            Description = "订购失败-其他原因";
        } else if (code.equals("00040005")) {
            Description = "登陆失败-密码错误";
        } else if (code.equals("00040006")) {
            Description = "登陆失败-其他原因";
        } else if (code.equals("00040007")) {
            Description = "修改失败-原始密码错误";
        } else if (code.equals("00040008")) {
            Description = "修改失败-其他原因";
        } else if (code.equals("00040009")) {
            Description = "初始化密码失败-验证码错误";
        } else if (code.equals("00040010")) {
            Description = "初始化密码失败-其他原因";
        } else if (code.equals("00040011")) {
            Description = "获取失败";
        } else if (code.equals("00040004")) {
            Description = "注册失败-其他原因";
        } else if (code.equals("00040012")) {
            Description = "注册失败-用户已注册";
        } else if (code.equals("00040013")) {
            Description = "获取用户信息失败";
        } else if (code.equals("00040014")) {
            Description = "获取通话记录失败";
        } else if (code.equals("00040015")) {
            Description = "获取短信记录失败";
        } else if (code.equals("00040016")) {
            Description = "MO短信失败";
        } else if (code.equals("00040017")) {
            Description = "验证码已过期";
        } else if (code.equals("00040018")) {
            Description = "号码不能匹配";
        } else if (code.equals("00040019")) {
            Description = "验证码错误";
        } else if (code.equals("00040020")) {
            Description = "用户未注册";
        } else if (code.equals("00040021")) {
            Description = "验证码请求次数频繁";
        } else if (code.equals("00040022")) {
            Description = "用户已登出";
        } else if (code.equals("00040031")) {
            Description="其他原因";
        } else {
            Description = "连接服务器异常";
        }
        return Description;
    }

    /**
     *
     * 登陆流程回调接口返回值转化为错误描述
     *
     * 00040005  10001
     *登陆失败-密码错误password error
     *00040006 10002
     *登陆失败-其他原因Login failed for other reason
     *00040017 10003
     *验证码已过期 Verification code has been expired
     *00040020 10004
     *用户未注册 Users are not registered
     *00040019 10005
     *验证码错误 Verification code error
     *
     * @param code
     * @return
     */
    public static String GetInterFaceDescription(String code) {
        String Description = "";
        if (code.equals("10001")) {
            Description = GetDescription("00040005");
        } else if (code.equals("10002")) {
            Description = GetDescription("00040006");
        } else if (code.equals("10003")) {
            Description = GetDescription("00040017");
        } else if (code.equals("10004")) {
            Description = GetDescription("00040020");
        } else if (code.equals("10005")) {
            Description = GetDescription("00040019");
        } else if (code.equals("10006")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("10007")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("10008")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("10009")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("10010")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("10011")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("12000")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("12001")) {
            Description = GetSDKDescription(code);
        } else if (code.equals("11001")) {
            Description ="连接网络超时";
        }
        else {
            Description = "连接服务器异常";
        }
        return Description;
    }


    /**
     * 网络接口返回code 、SDK回调接口返回的code 转换为登陆回调接口code
     * 11000 为不能识别
     * @param code
     * @return
     */
    public static String GetInterFaceCode(String code) {
        String InterFaceCode = "";
        if (code.equals("00040005")) {
            InterFaceCode = "10001";
        } else if (code.equals("00040006")) {
            InterFaceCode = "10002";
        } else if (code.equals("00040017")) {
            InterFaceCode = "10003";
        } else if (code.equals("00040020")) {
            InterFaceCode = "10004";
        } else if (code.equals("00040019")) {
            InterFaceCode = "10005";
        } else if (code.equals("0")) {
            InterFaceCode = "12000";
        }else if (code.equals("1")) {
            InterFaceCode = "12001";
        } else if (code.equals("2")) {
            InterFaceCode = "10006";
        } else if (code.equals("3")) {
            InterFaceCode = "10007";
        } else if (code.equals("4")) {
            InterFaceCode = "10008";
        } else if (code.equals("5")) {
            InterFaceCode = "10009";
        } else if (code.equals("6")) {
            InterFaceCode = "10010";
        } else if (code.equals("7")) {
            InterFaceCode = "10011";
        }else if (code.equals("8")) {
            InterFaceCode = "10012";
        }else if (code.equals("9")) {
            InterFaceCode = "10013";
        }else if(code.equals("")){

        }else {
            InterFaceCode = "11000";
        }
        return InterFaceCode;
    }

    /**
     * 网络接口返回code 转换为登陆回调接口code
     * 11000 为不能识别
     * @param code
     * @return
     */
    public static String GetSDKDescription(String code) {
        String SDKDescription = "";
        if (code.equals("12000")) {
            SDKDescription = "登录成功，手机已关机";
        } else if (code.equals("12001")) {
            SDKDescription = "登录成功，手机未关机";
        } else if (code.equals("10006")) {
            SDKDescription = "登录失败，手机号码未注册";
        } else if (code.equals("10007")) {
            SDKDescription = "登录失败，位置更新超时";
        } else if (code.equals("10008")) {
            SDKDescription = "登录菊风服务器失败";
        } else if (code.equals("10009")) {
            SDKDescription = "sip刷新失败";
        } else if (code.equals("10010")) {
            SDKDescription = "sip注册失败";
        } else if (code.equals("10011")) {
            SDKDescription = "鉴权失败，需要重新鉴权,无需验证码";
        }else if (code.equals("10012")) {
            SDKDescription = "鉴权失败，需要重新鉴权，带验证码";
        }else if (code.equals("10013")) {
            SDKDescription = "请求异常";
        }else if (code.equals("10014")) {
            SDKDescription = "鉴权失败，需要重新鉴权，验证码错误";
        }else {
            SDKDescription = "登录菊风服务器异常";
        }
        return SDKDescription;
    }
}
