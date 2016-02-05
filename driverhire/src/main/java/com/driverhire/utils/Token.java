package com.driverhire.utils;

import java.util.Random;

import org.apache.log4j.Logger;

public class Token {
	
	private static final Logger logger = Logger.getLogger(Token.class);
	public static String generateToken(int length){
		logger.info("inside generateToken");
		char cons[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		Random randomGenerator = new Random();
		StringBuffer s = new StringBuffer();
		System.out.println("cons length :" + cons.length);
		for(int i=0; i< length; i++) {
		 int randomInt = randomGenerator.nextInt(62);
		  s.append(cons[randomInt]);
		}
		logger.info("generatedToken :" + s.toString());
		return s.toString();
	}
}
