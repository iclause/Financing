package com.mga.financing.net;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.ebupt.jlog.JLog;
import com.mga.financing.safe.Md5Encrypt;


/**
 * 网络通用配置
 * Created by Administrator on 2016/6/6.
 */
public class Api {
    /**  测试环境与生产环境配置切换
     *      1修改三方地址（在Api文件中）
     *      2修改values 目录下config.xml中EBSDK（菊风）配置
     */
    /**
     * 三方地址配置（测试环境）
     */
//    public static final String appkey = "sejp0bXiLGkbuPoYGVvO833y6NFMrMj5";//内测三方服务器
//    public static final String appsecret = "4nNkCdivWQimioAtXghYLN390A0YXQxj";//内测三方服务器
//    public static final String HOST = "https://123.56.15.3:37443/wificalling_appserver/v1_1/";//内测公网地址

//    public static final String HOST = "https://123.56.15.3:37443/wificalling_appserver/v1/";//内测公网地址
    /**
     * 三方地址配置（生产环境）
     */
    public static final String appkey = "P2imLpO3xQCaHFR9upaIiPunSLrzZ3e1";//三方服务器
    public static final String appsecret = "bOfdnMqhF4dDqGEfAL9OwSke6HiCvvPU";//三方服务器
    public static final String HOST = "https://datavoice.ebopencloud.com:31443/wificalling_appserver/v1_1/";

    /**
     * 三方地址配置（很早之前的测试环境，已弃用）
     */
//    public static final String appkey = "u6579c2fe77f414db6e7384d5e64141e";//三方服务器
//    public static final String appsecret = "9sdnw3r3nfnu773rndnmk99yhfdewaf0";//三方服务器
//    public static final String HOST = "http://123.56.15.3:8081/wificalling_appserver/v1/";



    public static final String HOSTLOG = "http://123.56.15.3:8081/";

//    public static final String GETDOMAIN = HOST2 + "getdomain";
    public static final String UPDATELOCATION = HOST + "updatelocation";
    public static final String QUERYLOCATION = HOST + "updatelocationselect";
//    public static final String REPORT = HOST2 + "report";


    public static final String SMS_VERIFICATION_CODE = HOST + "sms_verification_code";//验证码
    public static final String USER_LOGOUT = HOST + "user_logout";//登出
    public static final String USER_REGISTER = HOST + "user_register";//注册
    public static final String USER_SUBSCRIBE = HOST + "user_subscribe";//订购
    public static final String USER_LOGIN = HOST + "user_login";//登录
    public static final String PASSWORD_CHECK = HOST + "password_check";//登录
    public static final String PASSWORD_REVERT = HOST + "password_revert";//密码重置、找回
    public static final String BUSINESS_INFO = HOST + "business_info";//获取业务信息
    public static final String ADVERTISEMENT_LIST = HOST + "advertisement_list";//获取最新活动
    public static final String MISSRECORD_LIST = HOST + "missrecord_list";//获取业务信息
    public static final String MISSMSG_LIST = HOST + "missmsg_list";//收短信
    public static final String SYSMSG_LIST = HOST + "sysmsg_list";//收短信
    public static final String USER_INFO = HOST + "user_info";//获取个人信息
    public static final String MOSMS = HOST + "mosms";//发短信
    public static final String VERSION_UPDATING = HOST + "version_updating";//版本更新

    public static final String UPLOAD = HOSTLOG + "/android/upload";//位置更新

    public static final String RECHARGE_TYPE=HOST+"user_recharge";//充值类型
    public static final String CALLRECORDSDETAIL=HOST+"usercalllog_list";//获取通话详单列表
    public static final String USERBILLLIST=HOST+"user_billlist";//获取用户账单详情列表
    public static final String PAYMENT_INFO=HOST+"payment_info";//获取用户支付订单详情列表
    public static final String GETMISSRECORDLIST=HOST+"missrecord_list";//获取未送达通话记录
    public static final String GETINVITECODE=HOST+"user_invitecode";//获取邀请码
    public static final String GETTRUSTSTATE=HOST+"updatelocationselect";//手机托管状态查询
    public static final String MARITIME_SUBSCRIBEPACKAGE=HOST+"user_subscribe";//订购套餐（全海通）
    public static final String MARITIME_CHANGEPACKAGE=HOST+"change_package";//修改套餐（全海通）
    public static final String MARITIME_CANCELPACKAGE=HOST+"cancel_package";//取消套餐（全海通）
    public static final String MARITIME_GETPACKAGEMESSAGE=HOST+"business_info";//获取业务套餐信息（全海通）
    public static final String CANCLE_PACKAGEAUTOSUB=HOST+"user_cancelpackageautosub";//取消续订套餐（华侨通）
    public static final String SET_PACKAGEAUTOSUB=HOST+"user_setpackageautosub";//设置续订套餐（华侨通）
    public static final String ACTIVE_PACKAGE=HOST+"user_packageactive";//激活套餐（环球风）
    public static final String ALL_PACKAGEINFO=HOST+"user_allpackageinfo";//用户所有操作套餐的信息

//    支付
    public static final String PREORDER=HOST+"user_payment_orderstr";//获取支付请求订单信息（支付宝/微信）
    public static final String PAYMENTQUERY=HOST+"payment_query";//查询支付结果（支付宝/微信）
    public static final String TRADECANCLE=HOST+"trade_cancel";//撤销（支付宝/微信）

    // ebupt
    public static final String appkeydomain = "3e142db90dca68dd2571f6fc1ae80d00";
    public static final String appsecretdomain = "49f911f0535426ee6e580a9ff73e655f";

    public static final String devicetype = "android";
    public static  int AUDIOMODE = 101; //设置语音编码格式
    public static final int AMR = 100;
    public static final int G711 = 101;
//    //安全控制服务器秘钥
//    public static final String APPKEYSAFESERVER = "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC";
//    public static final String APPSECRETSAFESERVER = "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC";
    //安全控制服务器秘钥
    public static final String APPKEYSAFESERVER = "GKbvRCX5Ihzn4UOjshJpky7sVZdzHUGN";
    public static final String APPSECRETSAFESERVER = "nutf8oDr08trfrl2gx09YFLcxENJ3n2G";
    //菊风protal   appkekey
    public static final String JUSTALK_KEY = "eb8ad14054081c54a523409f";
    //菊风protal   域名
    public static final String JUSTALK_IP = "http:router.justalkcloud.com:8080";
    //菊风server   登录密码
    public static String JUSTTALK_PWD="ebupt";
    public static String imei;
    public static String timestamp;
    /**
     * 获取三方服务器签名
     * @param time
     * @return
     */
    public static String getSign(Long time) {
//        JLog.d("3sign",String.valueOf(time/1000)+appsecret1);
        String sign = Md5Encrypt.getMD5(String.valueOf(time / 1000) + appsecret);
        JLog.d("截取前sign", sign);
        sign = sign.substring(16);
        JLog.d("截取后16位3sign", sign);
        return sign;
    }

    public static String getImei(Context c) {
        return ((TelephonyManager) c.getSystemService(c.TELEPHONY_SERVICE)).getDeviceId();
    }


}
