package com.ebupt.smssend.net;

/**
 * Created by mga on 2017/6/20 16:17.
 */
public class NetCode {

    public static final int LOGIN_OK = 0;
    //EXCEPTION
    public static final int SOCKETTIMEOUTEXCEPTION = 1000001;//服务器响应超时
    public static final int CONNECTEXCEPTION = 1000004;//服务器请求超时
    public static final int SERVERERRO = 1000003;
    public static final String SOCKETTIMEOUTEXCEPTIONSTR = "SOCKETTIMEOUTEXCEPTIONSTR";
    public static final String CONNECTEXCEPTIONSTR = "CONNECTEXCEPTIONSTR";
    public static final String SERVERERRORSTR = "SERVERERRORSTR";

// case 20000001:
//         request_param), intent);
//                break;
//            case 20000002:
//                    unknown_user), intent);
//                break;
//            case 20000004:
//                    auth_faild), intent);
//                break;
//            case 20000005:
//                    send_verificationcode_failed), intent);
//                break;
//            case 20000006:
//                    code_request_too_often), intent);
//                break;
//            case 20000007:
//                    code_failed_for_need_currently_using_sim_card), intent);
//                break;
//            case 20000008:
//                    verification_code_error), intent);
//                break;
//            case 20000009:
//                    verification_code_has_been_expired), intent);
//                break;
//            case 20000010:
//                    login_failed_for_need_verificationcode), intent);
//                break;
//            case 20000011:
//                    login_failed_for_need_the_right_place), intent);
//                break;
//            case 20000012:
//                    login_failed_for_other_reason), intent);
//                break;
//            case 20000013:
//                    users_are_not_registered), intent);
//                break;
//            case 30000001:
//                    service_error), intent);

    //    sdk auth/authcode
    public static final int REQUEST_PARAM = 20000001;
    public static final int UNKNOWN_USER = 20000002;
    public static final int AUTH_FAILD = 20000004;
    public static final int SEND_VERIFICATIONCODE_FAILED = 20000005;
    public static final int CODE_REQUEST_TOO_OFTEN = 20000006;
    public static final int CODE_FAILED_FOR_NEED_CURRENTLY_USING_SIM_CARD = 20000007;
    public static final int VERIFICATION_CODE_ERROR = 20000108;
    public static final int THIRDPARTY_VERIFICATION_CODE_ERROR=20000008;
    public static final int VERIFICATION_CODE_HAS_BEEN_EXPIRED = 20000009;
    public static final int LOGIN_FAILED_FOR_NEED_VERIFICATIONCODE = 20000010;
    public static final int LOGIN_FAILED_FOR_NEED_THE_RIGHT_PLACE = 20000011;
    public static final int LOGIN_FAILED_FOR_OTHER_REASON = 20000012;
    public static final int USERS_ARE_NOT_REGISTERED = 20000013;
    public static final int SERVICE_ERROR = 30000001;

    //    //    三方服务器登录
    public static final int SERVER3_LOGIN_PASSWORD_ERROR = 20000119;
    public static final int SERVER3_LOGIN_USER_NOT_OPEN_ACCOUNT = 20000113;
//zhuce

//20000112	"User already open account", --用户已经开户
//20000118	"User open account failed",    --用户开户失败

    public static final int SERVER3_REGISTOR_USER_ALREADY_OPEN_ACCOUNT = 20000112;
    public static final int SERVER3_REGISTOR_USER_OPEN_ACCOUNT_FAILED = 20000118;



//    服务器异常、超时
    public static final int SERVER_ERROR = 4000000;
    public static final int SERVER_TIMEOUT = 4000001;
    public static final int NO_NET = 4000002;




}
