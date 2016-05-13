package com.springapp.mvc.controller;

import com.google.common.collect.Maps;
import com.springapp.mvc.constant.WechatConfig;
import com.springapp.mvc.domain.WechatScanPayParamDo;
import com.springapp.mvc.util.QRcodeUtil;
import com.springapp.mvc.util.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
@Controller
public class WxScanPayController {

    public static final String UTF8_CHARSET = "UTF-8";
    public static final String SCAN_PAY_ERCODE_URL = "weixin://wxpay/bizpayurl?";

    @RequestMapping(value = "/wechat/pay.html",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        Long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = WechatUtil.createNoncestr(20);
        String productId = "2315321532";
        WechatScanPayParamDo wechatScanPayParamDo = new WechatScanPayParamDo();
        wechatScanPayParamDo.setAppid(WechatConfig.APP_ID);
        wechatScanPayParamDo.setMch_id(WechatConfig.MCH_ID);
        wechatScanPayParamDo.setProduct_id(productId);
        wechatScanPayParamDo.setTime_stamp(timestamp + "");
        wechatScanPayParamDo.setNonce_str(nonceStr);

        //参数排序
        SortedMap<String, String> parameters = Maps.newTreeMap();
        Field[] fields = wechatScanPayParamDo.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (null != WechatUtil.getFieldValueByName(fields[i].getName(), wechatScanPayParamDo)) {
                parameters.put(fields[i].getName(), WechatUtil.getFieldValueByName(fields[i].getName(),
                        wechatScanPayParamDo) + "");
            }
        }
        String payUrl = SCAN_PAY_ERCODE_URL + "sign=" + WechatUtil.createSign(parameters) + "&appid=" + WechatConfig.APP_ID + "&mch_id=" + WechatConfig.MCH_ID + "&product_id=" + productId +
                "&time_stamp=" + timestamp + "&nonce_str=" + nonceStr;
        QRcodeUtil.generateDimensionalCode(payUrl, productId);
        return "pay";
    }



    public static void main(String[] args) {
        Long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = WechatUtil.createNoncestr(20);
        String productId = "2315321532";
        WechatScanPayParamDo wechatScanPayParamDo = new WechatScanPayParamDo();
        wechatScanPayParamDo.setAppid(WechatConfig.APP_ID);
        wechatScanPayParamDo.setMch_id(WechatConfig.MCH_ID);
        wechatScanPayParamDo.setProduct_id(productId);
        wechatScanPayParamDo.setTime_stamp(timestamp + "");
        wechatScanPayParamDo.setNonce_str(nonceStr);

        //参数排序
        SortedMap<String, String> parameters = Maps.newTreeMap();
        Field[] fields = wechatScanPayParamDo.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (null != WechatUtil.getFieldValueByName(fields[i].getName(), wechatScanPayParamDo)) {
                parameters.put(fields[i].getName(), WechatUtil.getFieldValueByName(fields[i].getName(),
                        wechatScanPayParamDo) + "");
            }
        }
        System.out.println(SCAN_PAY_ERCODE_URL + "sign=" + WechatUtil.createSign(parameters)+"&appid=" + WechatConfig.APP_ID + "&mch_id=" + WechatConfig.MCH_ID + "&product_id=" + productId +
                "&time_stamp=" + timestamp + "&nonce_str=" + nonceStr);
    }

}
