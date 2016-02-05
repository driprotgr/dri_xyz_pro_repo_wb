package com.driverhire.dto;

public class LoginJson {
	private String user_mobile;
	private String user_password;
	private String gcm_identifier_id;
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getGcm_identifier_id() {
		return gcm_identifier_id;
	}
	public void setGcm_identifier_id(String gcm_identifier_id) {
		this.gcm_identifier_id = gcm_identifier_id;
	}
}
