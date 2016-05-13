package com.springapp.mvc.controller;

import com.springapp.mvc.biz.WechatCreateOrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Administrator on 2015/10/10 0010.
 */
@Controller
public class WxCreateOrderController {

    @Autowired
    private WechatCreateOrderBiz wechatCreateOrderBiz;

    @RequestMapping(value = "/wechat/order.html",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        wechatCreateOrderBiz.createOrder();

        return "pay";
    }
}
