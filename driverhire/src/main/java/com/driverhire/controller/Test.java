package com.driverhire.controller;

import javax.security.auth.login.LoginContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.driverhire.dto.LoginJson;


@Controller
@RequestMapping(value = "/test")
public class Test {
	private static final Logger log = Logger.getLogger(Test.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody LoginJson test() {
		log.info("test ");
		LoginJson json = new LoginJson();
		json.setGcm_identifier_id("abc");
		json.setUser_mobile("1234");
		json.setUser_password("122345");
		return json;
	}
}
