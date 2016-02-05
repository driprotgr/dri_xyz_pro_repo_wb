package com.driverhire.dto;

import java.util.Date;

public class UserDto {
	private String userName;
	private String userEmail;
	private String userCategory;
	private Long userId;
	private String userMobile;
	private String password;
	private char accountType;
	private String accountId; 
	private char actFlag;
	private String otp;
	
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getAccountType() {
		return accountType;
	}
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public char getActFlag() {
		return actFlag;
	}
	public void setActFlag(char actFlag) {
		this.actFlag = actFlag;
	} 	
	
	
}
