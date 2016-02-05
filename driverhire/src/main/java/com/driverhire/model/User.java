package com.driverhire.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;	
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	@Column(name = "USER_CATEGORY")
	private String userCategory;

	@Column(name = "USER_MOBILE")
	private String userMobile;
	@Column(name = "USER_PASSWORD")
	private String password;
	@Column(name = "USER_ACCOUNT_TYPE")
	private char accountType;
	@Column(name = "USER_ACCOUNT_ID")
	private String accountId;
	@Column(name = "CREATED_DATE")
	private Date createdDate; 
	@Column(name = "USER_ACT_FLAG")
	private char actFlag;
	@Column(name = "OTP")
	private String otp;
	@Column(name = "OTP_EXPIRY_DATE")
	private Date otpExpiryDate;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public char getActFlag() {
		return actFlag;
	}
	public void setActFlag(char actFlag) {
		this.actFlag = actFlag;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getOtpExpiryDate() {
		return otpExpiryDate;
	}
	public void setOtpExpiryDate(Date otpExpiryDate) {
		this.otpExpiryDate = otpExpiryDate;
	}
	
}
