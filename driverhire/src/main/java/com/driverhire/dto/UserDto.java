package com.driverhire.dto;

public class UserDto {
	private String name;
	private String email;
	private String category;
	private long id;
	private String mobile;
	private String password;
	private char accountType;
	private String accountId; 
	private boolean actFlag;
	private String otp;
	private String authToken;
	private String gcmIdentifierId;
	private String deviceId;
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Name :" + name);
		s.append('\n');
		s.append("Email :" + email);
		s.append('\n');
		s.append("Category :" + category);
		s.append('\n');
		s.append("Id :" + id);
		s.append('\n');
		s.append("mobile :" + mobile);
		s.append('\n');
		s.append("accountType :" + accountType);
		s.append('\n');
		s.append("accountId :" + accountId);
		s.append('\n');
		s.append("actFlag :" + actFlag);
		s.append('\n');
		s.append("otp :" + otp);
		s.append('\n');
		s.append("authToken :" + authToken);
		s.append('\n');
		s.append("gcmIdentifierId :" + gcmIdentifierId);
		s.append('\n');
		s.append("deviceId :" + deviceId);
		s.append('\n');
		return s.toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public boolean isActFlag() {
		return actFlag;
	}
	public void setActFlag(boolean actFlag) {
		this.actFlag = actFlag;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getGcmIdentifierId() {
		return gcmIdentifierId;
	}
	public void setGcmIdentifierId(String gcmIdentifierId) {
		this.gcmIdentifierId = gcmIdentifierId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	
}
