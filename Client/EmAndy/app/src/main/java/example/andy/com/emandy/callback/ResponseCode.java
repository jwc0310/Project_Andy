package example.andy.com.emandy.callback;

public interface ResponseCode {
    /**
     * 操作成功
     */
    public static final String SUCCESS = "000000";
    /**
     * 系统异常
     */
    public static final String SYS_ERROR = "999999";
    /**
     * 无效操作
     */
    public static final String FAILED = "900000";
    /**
     * 客户端版本过低,请及时更新
     */
    public static final String CLIENT_VERSION_LOW = "900001";
    /**
     * 网络不给力,请求服务器延时
     */
    public static final String NEW_ERROR_READ_TIME_OUT = "900002";
    /**
     * 访问受限
     */
    public static final String DENY_ACCESS = "900003";
    /**
     * 输入错误
     */
    public static final String INPUT_ERROR = "900004";
    /**
     * 参数错误
     */
    public static final String PARAM_ERROR = "900005";
    /**
     * HEADER错误
     */
    public static final String CLIENT_INVALID = "900006";
    /**
     * 用户已存在
     */
    public static final String ALDERLT_EXIST = "500000";
    /**
     * 验签错误
     */
    public static final String VERIFY_SINNATURE_ERROR = "900007";
    /**
     * IP权限不足
     */
    public static final String IP_JURISDICTION_ERROR = "900008";
    /**
     * 请检查接口访问地址是否正确
     */
    public static final String CALL_URL_ERROR = "900009";
    /**
     * 请输入手机号
     */
    public static final String LOGIN_ERROR = "200001";
    /**
     * 第三方认证失败
     */
    public static final String LOGIN_THIRD_PART_ERROR = "200002";
    /**
     * 未绑定手机
     */
    public static final String MOBILE_NOT_BINDING = "200003";
    /**
     * 手机不存在
     */
    public static final String MOBILE_NOT_EXIST = "200004";
    /**
     * 该手机已绑定该平台账号,如需绑定,请登陆账号解除绑定
     */
    public static final String MOBILE_BINDING = "200005";
    /**
     * 没有登录或登录失效,请重新登录
     */
    public static final String NEED_LOGIN = "200006";
    /**
     * 请完善信息
     */
    public static final String NOT_PERFECT_USER_INFO = "200007";

    /**
     * 重复昵称
     */
    public static final String NICK_NAME_EXIST_ERROR = "200012";
    /**
     * 短信验证码发送失败
     */
    public static final String SMS_SEND_FAILD = "300001";
    /**
     * 短信验证码过期或不存在,请重新获取
     */
    public static final String SMS_CODE_TIMEOUT = "300002";
    /**
     * 短信验证码错误
     */
    public static final String SMS_CODE_ERROR = "300003";
    /**
     * 短信验证码已下发,1分钟内使用有效
     */
    public static final String SMS_CODE_MULTPLE = "300004";
    /**
     * 请在一分钟后再获取验证码
     */
    public static final String SMS_CODE_REPEAT = "300005";

    /**
     * 动态已被删除
     */
    public static final String DELETED_PRODUCT = "700002";

    /**
     * 用户已签到
     */
    public static final String USER_SIGN_IN_EXIST = "200010";

    /**
     * 不存在的投票活动
     */
    public static final String NOT_EXIST_EVENT = "600005";

    /**
     * 已超过报名时间
     */
    public static final String APPLY_TIMEOUT_EVENT = "600006";

    /**
     * 已参加过投票
     */
    public static final String TODAY_VOTED_EVENT = "600007";

    /**
     * 已超过投票时间
     */
    public static final String VOTE_TIMEOUT_EVENT = "600008";

    /**
     * 已参加过报名
     */
    public static final String JOINED_EVENT = "600009";


}
