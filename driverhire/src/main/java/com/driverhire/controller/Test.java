package com.driverhire.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


//@Controller
//@RequestMapping(value = "/test")
public class Test {
	private static final Logger log = Logger.getLogger(Test.class);
	
	/*@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody LoginJson test() {
		log.info("test ");
		LoginJson json = new LoginJson();
		json.setGcm_identifier_id("abc");
		json.setUser_mobile("1234");
		json.setUser_password("122345");
		return json;
	}*/
	
	public static void main(String args[]) {
		
		String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://127.4.161.130:3306/user";
	    String username = "profile";
	    String password = "profile";

	    try {
	        Class.forName(driver);
	        Connection connection = DriverManager.getConnection(url, username,  password);
		
	       // event.getServletContext().setAttribute(ATTR_CONNECTION, connection);


	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
