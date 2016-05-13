package sign.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import sign.SignException;
import sign.SignManager;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Set;

/**
 * 签名校验管理器
 * <p>用于签名校验</p>
 */
@Service
public class SignManagerImpl implements SignManager {

    private static final Log LOGGER = LogFactory.getLog(SignManagerImpl.class);

    /**
     *需排除的参数
     */
    public static final String CALLBACK = "callback";
    public static final String T = "t";


    @Override
    public void check(HttpServletRequest request) throws SignException {

        String timestamp = request.getParameter(TIMESTAMP);
        if (null == timestamp) {
            throw new SignException("时间戳不能为空！");
        }
        String sign = request.getParameter(SIGN);
        if (null == sign) {
            throw new SignException("签名Key不能为空！");
        }

        String signMthod = request.getParameter(SIGN_METHOD);
        if (null == signMthod) {
            throw new SignException("签名方式不能为空！");
        }

        //升序排列所有的key
        Set<String> set = Sets.newTreeSet(request.getParameterMap().keySet());

        StringBuilder signKeyStr = new StringBuilder();
        for (String key : set) {
            if ("_".equals(key)) {
                continue;
            }
            if (CALLBACK.equals(key)) {
                continue;
            }
            if (T.equals(key)) {
                continue;
            }

            if (!SIGN.equals(key) && !SIGN_METHOD.equals(key)) {
                signKeyStr.append(key);
                String value ;
                try {
                    value = URLDecoder.decode(request.getParameter(key).trim(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("URLDecoder 异常！parameters ="
                            + ToStringBuilder.reflectionToString(request.getParameterMap()),e);
                    throw new SignException("URLDecoder 异常！");
                }
                signKeyStr.append(value == null ? "" : value);
            }
        }

        String key = signKeyStr+ EASCS_KEY;

        String secretSign = null;
        if (Strings.isNullOrEmpty(signMthod) || MD5.equals(signMthod)) {
            String md5key = DigestUtils.md5Hex(key);
            secretSign = Base64.encodeBase64String(md5key.getBytes());
        }

        if (null == secretSign || !sign.equals(secretSign)) {
            LOGGER.warn("签名不匹配！" + ToStringBuilder.reflectionToString(set)
                    + "secretSign =" + secretSign
                    + "sign =" + "sign");
            throw new SignException("签名校验错误！");
        }

    }
}
