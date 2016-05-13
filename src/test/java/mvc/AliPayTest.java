package mvc;

import com.springapp.mvc.config.AlipayConfig;
import com.springapp.mvc.sign.RSA;
import com.springapp.mvc.util.httpClient.HttpProtocolHandler;
import com.springapp.mvc.util.httpClient.HttpRequest;
import com.springapp.mvc.util.httpClient.HttpResponse;
import com.springapp.mvc.util.httpClient.HttpResultType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/13 0013.
 */
public class AliPayTest {
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?" + "_input_charset=" + AlipayConfig.input_charset;
    public static void main(String[] args) {

        sendData();
    }

    private static void postData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("service", "alipay.wap.create.direct.pay.by.user");
        params.put("partner", AlipayConfig.partner);
        params.put("seller_id", AlipayConfig.seller_id);
        params.put("_input_charset", AlipayConfig.input_charset);
        params.put("payment_type", "1");
        params.put("notify_url", "http://商户网关地址/new/aliy/notify");
        params.put("return_url", "http://商户网关地址/new/aliy/return");
        params.put("out_trade_no", "23534543626");
        params.put("subject", "订单名称");
        params.put("total_fee", "0.01");
        params.put("show_url", "http://www.商户网址.com/myorder.html");
        params.put("body", "测试商品");
        params.put("it_b_pay", "10m");


        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(params);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);

//        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+ AlipayConfig.input_charset);

        HttpResponse response = null;
        try {
            response = httpProtocolHandler.execute(request, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        if (response != null) {
            String strResult = null;
            try {
                strResult = response.getStringResult();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(strResult);
        }
    }

    private static void sendData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("service", "alipay.wap.create.direct.pay.by.user");
        params.put("partner", AlipayConfig.partner);
        params.put("seller_id", AlipayConfig.seller_id);
        params.put("_input_charset", AlipayConfig.input_charset);
        params.put("payment_type", "1");
        params.put("notify_url", "http://商户网关地址/new/aliy/notify");
        params.put("return_url", "http://商户网关地址/new/aliy/return");
        params.put("out_trade_no", "23534543626");
        params.put("subject", "订单名称");
        params.put("total_fee", "0.01");
        params.put("show_url", "http://www.商户网址.com/myorder.html");
        params.put("body", "测试商品");
        params.put("it_b_pay", "10m");


        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(params);
        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayConfig.input_charset);

//        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+ AlipayConfig.input_charset);

        HttpClient httpclient = new HttpClient();
        PostMethod httpPost =new PostMethod(ALIPAY_GATEWAY_NEW+"_input_charset="+ AlipayConfig.input_charset);

//        NameValuePair[] param =generatNameValuePair(sPara);
//        httpPost.setRequestBody(param);
        httpPost.setFollowRedirects(true);
        try {
            httpclient.executeMethod(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(AlipayConfig.sign_type.equals("RSA") ){
            mysign = RSA.sign(prestr, AlipayConfig.private_key, AlipayConfig.input_charset);
        }
        return mysign;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static List<NameValuePair> generatNameValuePairList(Map<String, String> properties) {
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }

        return nameValuePair;
    }
}
