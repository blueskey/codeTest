package mvc;

import com.google.common.collect.Maps;
import com.springapp.mvc.constant.EncodeConstants;
import com.springapp.mvc.constant.WechatConfig;
import com.springapp.mvc.domain.WechatCreateOrderParamDo;
import com.springapp.mvc.util.WechatUtil;
import com.springapp.mvc.util.XmlUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
public class WechatPublicPay {

    public static final String CREATE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static void createOrder() {
        WechatCreateOrderParamDo wechatCreateOrderParamDo = new WechatCreateOrderParamDo();
        wechatCreateOrderParamDo.setNonce_str(WechatUtil.createNoncestr(20));
        wechatCreateOrderParamDo.setAppid(WechatConfig.APP_ID);
        wechatCreateOrderParamDo.setMch_id(WechatConfig.MCH_ID);
        wechatCreateOrderParamDo.setBody("测试商品");
        wechatCreateOrderParamDo.setOut_trade_no("13534534623623");
        wechatCreateOrderParamDo.setTotal_fee(1);
        wechatCreateOrderParamDo.setSpbill_create_ip("127.0.0.1");
        wechatCreateOrderParamDo.setTrade_type("NATIVE");
        wechatCreateOrderParamDo.setNotify_url("http://www.baidu.com");

        try {
            StringEntity myEntity ;
            myEntity = new StringEntity(createOrderPacketXml(wechatCreateOrderParamDo), EncodeConstants.UTF8_CHARSET);


            HttpPost httpPost = new HttpPost(CREATE_ORDER_URL);
            httpPost.setEntity(myEntity);
                System.out.println("executing request" + httpPost.getRequestLine());
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String text;
                StringBuffer responseText = new StringBuffer();
                while ((text = bufferedReader.readLine()) != null) {
                    responseText.append(text);
                }
                Map<String, String> map = XmlUtil.xml2Map(responseText.toString());
                System.out.println("调用统一下单接口，返回内容："+responseText.toString());
                if (map.containsKey("return_code")) {
                    if ("SUCCESS".equals(map.get("return_code"))){
                        if (map.containsKey("result_code") && "SUCCESS".equals(map.get("result_code"))) {
                            System.out.println("统一下单成功!+商户订单号="+wechatCreateOrderParamDo.getOut_trade_no());
                            return;
                        }
                    } else if ("FAIL".equals(map.get("return_code"))) {
                        if (map.containsKey("return_msg")) {
                            System.out.println("统一下单失败!+商户订单号="+wechatCreateOrderParamDo.getOut_trade_no()+",返回错误："+
                                    map.get("return_msg"));
                        }
                        return;
                    }
                }

            }
            EntityUtils.consume(entity);
        } catch (IOException e) {

        } catch (JDOMException e) {
        }
    }

    private static String createOrderPacketXml(WechatCreateOrderParamDo wechatCreateOrderParamDo) {

        //参数排序
        SortedMap<String, String> parameters = Maps.newTreeMap();
        Field[] fields = wechatCreateOrderParamDo.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (null != WechatUtil.getFieldValueByName(fields[i].getName(), wechatCreateOrderParamDo)) {
                parameters.put(fields[i].getName(), WechatUtil.getFieldValueByName(fields[i].getName(),
                        wechatCreateOrderParamDo) + "");
            }
        }

        parameters.put("sign", WechatUtil.createSign(parameters));

        String createOrderRequestXml = WechatUtil.getRequestXml(parameters);
        System.out.println("统一下单xml:"+createOrderRequestXml);
        return createOrderRequestXml;
    }

    public static void main(String[] args) {
        createOrder();
    }
}
