package com.driverhire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.driverhire.controller.mobile.UserController;
import com.driverhire.dto.JsonResponse;
import com.driverhire.service.UserService;


public class SessionInterceptor implements HandlerInterceptor{

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object arg2) throws Exception {
		logger.info("Inside SessionInterceptor.preHandle");
		String uri = servletRequest.getRequestURI();
		logger.info("uri " + uri);
		
		if("/driverhire/mobile/login".equals(uri) || "/driverhire/mobile/login1".equals(uri) 
			|| "/driverhire/mobile/signup".equals(uri) || "/driverhire/mobile/confirmotp".equals(uri))
			return true;
		
		
		/*Enumeration e = servletRequest.getParameterNames();
		
		while (e.hasMoreElements()){
	         logger.info("====" + e.nextElement()); 
	      }
		
		logger.info("==========================================================================");
		
		e = servletRequest.getAttributeNames();
		while (e.hasMoreElements()){
	         logger.info("====" + e.nextElement()); 
	     }
		BufferedReader br = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
        String json = "";
        if(br != null){
        	String temp = "";
        	do {
        		temp = br.readLine();
        		json = json + temp;
        	} while(temp != null);
        }
        
        logger.info("json" + json);*/
		String sequrityAuth = servletRequest.getHeader("auth_token");
		logger.info("sequrityAuth :"+ sequrityAuth);
		boolean isAuthValid = userService.isSeqAuthValid(sequrityAuth);
		
		if(! isAuthValid) {
			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setResponseCode(701);
			//jsonResponse.setResponseBody("Invalid Session");
			jsonResponse.setResponseMessage("Invalid Session");
			logger.info(jsonResponse.toString());
			servletResponse.setContentType("application/json");
			servletResponse.getWriter().write(jsonResponse.toString());
		}
		
		return isAuthValid;
	}

}
