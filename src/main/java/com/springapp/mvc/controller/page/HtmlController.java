package com.springapp.mvc.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
@Controller
public class HtmlController {

    @RequestMapping(value = "/pages/lunbo.html",method = RequestMethod.GET)
    public String lunbo(ModelMap model) {
        return "pages/lunbo";
    }

    @RequestMapping(value = "/pages/magnifyingGlass.html",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "pages/magnifyingGlass";
    }
}
