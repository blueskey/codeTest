package com.springapp.mvc.biz.impl;

import com.google.common.collect.Maps;
import com.springapp.mvc.biz.WechatCreateOrderBiz;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
@Service
public class WechatCreateOrderBizImpl implements WechatCreateOrderBiz {

    public static final String CREATE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatCreateOrderBizImpl.class);

    @Override
    public void createOrder() {
        WechatCreateOrderParamDo wechatCreateOrderParamDo = new WechatCreateOrderParamDo();
        wechatCreateOrderParamDo.setNonce_str(WechatUtil.createNoncestr(20));
        wechatCreateOrderParamDo.setAppid(WechatConfig.APP_ID);
        wechatCreateOrderParamDo.setMch_id(WechatConfig.MCH_ID);
        wechatCreateOrderParamDo.setBody("测试商品");
        wechatCreateOrderParamDo.setOut_trade_no("13534534623623");
        wechatCreateOrderParamDo.setTotal_fee(1);
        wechatCreateOrderParamDo.setSpbill_create_ip("127.0.0.1");
        wechatCreateOrderParamDo.setTrade_type("JSAPI");
        wechatCreateOrderParamDo.setNotify_url("http://www.baidu.com");

        try {
            StringEntity myEntity ;
            myEntity = new StringEntity(createOrderPacketXml(wechatCreateOrderParamDo), EncodeConstants.UTF8_CHARSET);


            HttpPost httpPost = new HttpPost(CREATE_ORDER_URL);
            httpPost.setEntity(myEntity);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("executing request" + httpPost.getRequestLine());
            }
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),EncodeConstants.UTF8_CHARSET));
                String text;
                StringBuffer responseText = new StringBuffer();
                while ((text = bufferedReader.readLine()) != null) {
                    responseText.append(text);
                }
                Map<String, String> map = XmlUtil.xml2Map(responseText.toString());
                LOGGER.info("调用统一下单接口，返回内容："+responseText.toString());
                if (map.containsKey("return_code")) {
                    if ("SUCCESS".equals(map.get("return_code"))){
                        if (map.containsKey("result_code") && "SUCCESS".equals(map.get("result_code"))) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("统一下单成功!商户订单号="+wechatCreateOrderParamDo.getOut_trade_no());
                            }
                            return;
                        }
                    } else if ("FAIL".equals(map.get("return_code"))) {
                        if (map.containsKey("return_msg")) {
                            LOGGER.warn("统一下单失败!商户订单号="+wechatCreateOrderParamDo.getOut_trade_no()+",返回错误："+
                                    map.get("return_msg"));
                        }
                        return;
                    }
                }

            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            LOGGER.error("IO错误",e);
        } catch (JDOMException e) {
            LOGGER.error("JDOM错误", e);
        }
    }

    private String createOrderPacketXml(WechatCreateOrderParamDo wechatCreateOrderParamDo) {

        //��������
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
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("ͳһ�µ�xml:"+createOrderRequestXml);
        }
        return createOrderRequestXml;
    }
}
