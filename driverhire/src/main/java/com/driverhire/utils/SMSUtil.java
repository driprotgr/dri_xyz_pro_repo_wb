package com.driverhire.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.driverhire.controller.mobile.UserController;

public class SMSUtil {
	 /* Sms Gate Way AuthKey */
    public static final String SMS_GATE_WAY_AUTH_KEY = "42746xyao086645l8h97";    
    /* Sms Gate Way Sender Id */
    public static final String SMS_GATE_WAY_SENDER_ID = "SHCODE";
    public static final String SMS_GATE_WAY_FINAL_URL = "http://alerts.sinfini.com/api/web2sms.php?workingkey=" + SMS_GATE_WAY_AUTH_KEY + "&sender="
        + SMS_GATE_WAY_SENDER_ID + "&to=";
    private static final Logger logger = Logger.getLogger(SMSUtil.class);
public static boolean sendSMS(String mobileNO, String message) {
        boolean smsResult = false;
        try {
            if(checkNullAndEmpty(mobileNO) && checkNullAndEmpty(message)) {
                message = message.trim();
                String encodedMessage = URLEncoder.encode(message);
                String requestingUrl = SMS_GATE_WAY_FINAL_URL;
                requestingUrl = requestingUrl + mobileNO + "&message=" + encodedMessage;
                String inputLine, result = "";
                URI uri = new URI(requestingUrl);
                URL url = uri.toURL();
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                while((inputLine = in.readLine()) != null) {
                    result = result.concat(inputLine);
                }
                in.close();
                logger.error("SMS sent Successfully to -" + mobileNO + " and Message is " + message);
                logger.error("SMS RESULT - " + result);
                smsResult = true;
            }

        } catch (Exception e) {
            logger.error("error in Sending Sms", e);
        }

        return smsResult;
    }   

	public static boolean  checkNullAndEmpty(String s) {
		if(s == null || "".equals(s))
			return false;
		else 
			return true;
	}
	
	public static void main(String args[]) {
		sendSMS("9550561501", "sddfg");
	}
}
