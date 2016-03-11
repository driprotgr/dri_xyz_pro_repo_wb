package com.driverhire.controller.mobile;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.driverhire.dto.UserDto;
import com.driverhire.exception.DriverHireException;
import com.driverhire.service.UserService;
import com.driverhire.constant.AccountType;
import com.driverhire.dto.JsonResponse;
import com.driverhire.dto.LoginJson;

@Controller
@ControllerAdvice
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
			response.setResponseCode(200);
			response.setResponseBody(newUser);
		} catch (DriverHireException e) {
			response = new JsonResponse();
			response.setResponseCode(e.getErrCode());
			response.setResponseBody(e.getCause());
			response.setResponseMessage(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			response = new JsonResponse();
			response.setResponseCode(700);
			response.setResponseBody(e.getCause());
			response.setResponseMessage("DB Constrainnt Violated");
		} 
		catch (Exception e) {
			logger.error(e.getClass().getName());
			e.printStackTrace();
			response = new JsonResponse();
			response.setResponseCode(700);
			response.setResponseBody(e.getMessage());
			response.setResponseMessage(e.getMessage());
		}
		// newUser.setUserName(user.getUserName());
		
		return response;
	}
	
	@RequestMapping(value = "login1", method = RequestMethod.POST)
	/*public @ResponseBody JsonResponse login1(@RequestParam("user_mobile") String mobileNo, 
											@RequestParam("user_password") String password, 
											@RequestParam("gcm_identifier_id") String gcmIdentifier) 
	{*/
	public @ResponseBody JsonResponse login1(@RequestBody LoginJson json) {
		logger.info("Inside createUser controller");
		JsonResponse response = new JsonResponse();
		try {
			UserDto userDto= userService.logIn(json.getUser_mobile(), json.getUser_password(), json.getGcm_identifier_id());
			response.setResponseCode(200);
			response.setResponseBody(userDto);
			response.setAuthToken(userDto.getAuthToken());
			
		} catch (DriverHireException e) {
			
			response.setResponseCode(801);
			response.setResponseBody(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody JsonResponse login(@RequestBody UserDto user) {
		logger.info("Inside login controller");
		JsonResponse response;
		
		try {
			response = userService.logIn(user);
			response.setResponseCode(200);
			if(user.getAccountType() != AccountType.MOBILE.getValue()) {
				
			}
		} catch (DriverHireException e) {
			response = new JsonResponse();
			response.setResponseCode(e.getErrCode());
			response.setResponseBody(e.getCause());
			response.setResponseMessage(e.getMessage());
		}catch (DataIntegrityViolationException e) {
			response = new JsonResponse();
			response.setResponseCode(700);
			response.setResponseBody(e.getCause());
			response.setResponseMessage("DB Constrainnt Violated");
		} catch (ConstraintViolationException e) {
			response = new JsonResponse();
			response.setResponseCode(700);
			response.setResponseBody(e.getCause());
			response.setResponseMessage("DB Constrainnt Violated");
		} 
		catch (Exception e) {
			response = new JsonResponse();
			response.setResponseCode(700);
			response.setResponseBody(e.getMessage());
			response.setResponseMessage(e.getMessage());
		}
		return response;		
	}
	
	
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public void handle(HttpMessageNotReadableException e) {
	        logger.warn("Returning HTTP 400 Bad Request", e);
	        throw e;
	    }
	    @RequestMapping(value = "/confirmotp", method = RequestMethod.POST)
		public @ResponseBody JsonResponse confirmOtp(@RequestBody UserDto user) {
			logger.info("Inside confirmOtp controller");
			JsonResponse response = new JsonResponse();
			try{
				UserDto dbUser = userService.confirmSignupOtp(user);
				response.setResponseBody(dbUser);
				response.setResponseCode(200);
			}catch(DriverHireException e){
				response.setResponseCode(e.getErrCode());
				response.setResponseBody(e.getCause());
				response.setResponseMessage(e.getMessage());
			}catch (Exception e) {
				response.setResponseCode(700);
				response.setResponseBody(e.getMessage());
				response.setResponseMessage(e.getMessage());
			}
			return response;
	    }
	    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
		public @ResponseBody JsonResponse changePassword(@RequestBody UserDto user) {
			logger.info("Inside changePassword controller");
			JsonResponse response = new JsonResponse();
			try{
				UserDto dbUser = userService.changePassword(user);
				response.setResponseBody(dbUser);
				response.setResponseCode(200);
			}catch(DriverHireException e){
				response.setResponseCode(e.getErrCode());
				response.setResponseBody(e.getCause());
				response.setResponseMessage(e.getMessage());
			}catch (Exception e) {
				response.setResponseCode(700);
				response.setResponseBody(e.getMessage());
				response.setResponseMessage(e.getMessage());
			}
			return response;
	    }
	    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
		public @ResponseBody JsonResponse updateUser(@RequestBody UserDto user) {
			logger.info("Inside updateUser controller");
			JsonResponse response = new JsonResponse();
			try{
				UserDto dbUser = userService.updateUser(user);
				response.setResponseBody(dbUser);
				response.setResponseCode(200);
			}catch(DriverHireException e){
				response.setResponseCode(e.getErrCode());
				response.setResponseBody(e.getCause());
				response.setResponseMessage(e.getMessage());
			}catch (Exception e) {
				response.setResponseCode(700);
				response.setResponseBody(e.getMessage());
				response.setResponseMessage(e.getMessage());
			}
			return response;
	    }
	    
	    @RequestMapping(value = "/sendotp{userId}", method = RequestMethod.GET)
		public @ResponseBody JsonResponse sendOtp(@PathVariable String mobileNo) {
			logger.info("Inside sendOtp controller");
			JsonResponse response = new JsonResponse();
			try{
				UserDto user = new UserDto();
				user.setAccountId(mobileNo);
				user = userService.resendOtp(user);
				response.setResponseBody("OTP SENT");
				response.setResponseCode(200);
			}catch(DriverHireException e){
				response.setResponseCode(e.getErrCode());
				response.setResponseBody(e.getCause());
				response.setResponseMessage(e.getMessage());
			}catch (Exception e) {
				response.setResponseCode(700);
				response.setResponseBody(e.getMessage());
				response.setResponseMessage(e.getMessage());
			}
			return response;
	    }
}

