package sign;

import javax.servlet.http.HttpServletRequest;

/**
 * 签名校验管理器
 * <p>用于签名校验</p>
 */
public interface SignManager {

    /** 时间戳参数 */
    public static final String TIMESTAMP = "timestamp";

    /** signKey参数 */
    public static final String SIGN = "sign";

    /** 签名方式 */
    public static final String SIGN_METHOD = "sign_method";

    /** md5签名 */
    public static final String MD5 = "MD5";

    /** 公共key */
    public static final String EASCS_KEY = "AzpRbAlnViRSES==";



    /**
     *  签名校验
     * <p>
     * 保证会将Sign对象保存到请求attribute中</br>
     * </p>
     *
     * @param request
     * @throws SignException
     */
    public void check(HttpServletRequest request) throws SignException ;
}
