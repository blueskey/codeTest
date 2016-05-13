package com.springapp.mvc.controller;

import com.springapp.mvc.config.AlipayConfig;
import com.springapp.mvc.sign.RSA;
import com.springapp.mvc.util.AlipayCore;
import common.result.common.CommonResultCode;
import common.result.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sign.Sign;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2015/10/13 0013.
 */
@Controller
public class AlipayController {

    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?" + "_input_charset=" + AlipayConfig.input_charset;

    @RequestMapping(value = "/pay/pay.html",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("service", "alipay.wap.create.direct.pay.by.user");
        params.put("partner", AlipayConfig.partner);
        params.put("seller_id", AlipayConfig.seller_id);
        params.put("_input_charset", AlipayConfig.input_charset);
        params.put("payment_type", "1");
        params.put("notify_url", AlipayConfig.ALI_WAP_PAY_NOTIFY_PUR);
        params.put("return_url",AlipayConfig.ALI_WAP_PAY_RETURN_PUR);
        params.put("out_trade_no",createRandomNumberCode(10));
        params.put("subject", "订单名称");
        params.put("total_fee", "0.01");
        params.put("show_url", AlipayConfig.PAY_URL_PREFFIX+"/wechat/pay.html");
        params.put("body", "测试商品");
        params.put("it_b_pay", "10m");

        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(params);

        model.put("postParams", sPara);
        model.put("postUrl", ALIPAY_GATEWAY_NEW);

        return "aliyPay";
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
     * 随机生成定长数字串
     *
     * @return
     */
    private  String createRandomNumberCode(int length) {
        // 产生随机码
        StringBuilder sub = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sub.append(random.nextInt(10));
        }
        return sub.toString();
    }


    @Sign
    @ResponseBody
    @RequestMapping("/pay/test.json")
    public JsonResult test(String id, String name,HttpServletRequest request) {
        return new JsonResult(CommonResultCode.SUCCESS);
    }

}
