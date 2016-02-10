package com.driverhire.controller.mobile;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.driverhire.dto.UserDto;
import com.driverhire.exception.DriverHireException;
import com.driverhire.service.UserService;
import com.driverhire.dto.JsonResponse;
import com.driverhire.dto.LoginJson;

@Controller
@RequestMapping(value = "/mobile")
public class UserController {

private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody JsonResponse createUser(@RequestBody UserDto user) {
		logger.info("Inside createUser controller");

		JsonResponse response = new JsonResponse();
		UserDto newUser = null;
		try {
			newUser = userService.createUser(user);
		} catch (DriverHireException e) {
			logger.error("User already exists");
			response.setResponseCode(400);
			response.setResponseBody(e.getMessage());
			return response;
		}
		// newUser.setUserName(user.getUserName());
		response.setResponseCode(200);
		response.setResponseBody(newUser);
		return response;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	/*public @ResponseBody JsonResponse login(@RequestParam("user_mobile") String mobileNo, 
											@RequestParam("user_password") String password, 
											@RequestParam("gcm_identifier_id") String gcmIdentifier) 
	{*/
	public @ResponseBody JsonResponse login(@RequestBody LoginJson json) {
		JsonResponse response = new JsonResponse();
		try {
			UserDto userDto= userService.logIn(json.getUser_mobile(), json.getUser_password(), json.getGcm_identifier_id());
			response.setResponseCode(200);
			response.setResponseBody(userDto);
			response.setOthToken(userDto.getAuthToken());
			
		} catch (DriverHireException e) {
			
			response.setResponseCode(801);
			response.setResponseBody(e.getMessage());
		}
		return response;
	}
}
