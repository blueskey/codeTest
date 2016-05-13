package com.springapp.mvc.controller;

import com.springapp.mvc.util.AlipayNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/13 0013.
 */
@Controller
public class ReturnController {
    private static final Logger logger = LoggerFactory.getLogger(ReturnController.class);

    @RequestMapping(value = "/pay/return.html",method = RequestMethod.GET)
    public void printWelcome(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
//        logger.info("进入　pay/return ");
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            try {
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String trade_status = null;
        try {
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

//            logger.info(" pay return: out_trade_no = "+out_trade_no+",trade_no="+trade_no+",trade_status="+trade_status);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
            }
//            logger.info(" return success ");
            //该页面可做页面美工编辑
            out.println("验证成功<br />");
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
//            logger.info(" return fail ");
            //该页面可做页面美工编辑
            out.println("验证失败");
        }
    }
}
