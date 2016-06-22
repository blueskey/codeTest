package com.springapp.mvc.controller;

import com.google.common.collect.Lists;
import com.springapp.mvc.domain.UserDo;
import common.result.common.CommonResultCode;
import common.result.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sign.Sign;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		List<UserDo> userDos = Lists.newArrayList();
		UserDo userDo1 = new UserDo("lili", 23, new Date());
		UserDo userDo2 = new UserDo("kate", 18, new Date());
		UserDo userDo3 = new UserDo("", 4, new Date());
		UserDo userDo4 = new UserDo(null, 8, new Date());
		userDos.add(userDo1);
		userDos.add(userDo2);
		userDos.add(userDo3);
		userDos.add(userDo4);
		model.put("userDos", userDos);

		Map<String, UserDo> userDoMap = new HashMap<String, UserDo>();
		userDoMap.put(userDo1.getName(), userDo1);
		userDoMap.put(userDo2.getName(), userDo2);
		model.put("userDoMap", userDoMap);
		return "hello";
	}

	@RequestMapping(value = "/hello/test.json")
	@ResponseBody
	@Sign
	public JsonResult testSign() {
		return new JsonResult(CommonResultCode.SUCCESS);
	}
}