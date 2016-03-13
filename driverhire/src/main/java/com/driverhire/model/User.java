package com.driverhire.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name = "USER")
public class User {
	
	private Long userId;
	private String userName;
	private String userEmail;
	private String userCategory;
	private String userMobile;
	private String password;
	private char accountType;
	private String accountId;
	
	@Temporal (TemporalType.TIMESTAMP)
	private Date createdDate; 
	
	private char actFlag;
	private String otp;
	private Date otpExpiryDate;
	
	private List<UserSession> userSessions;
	
	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "USER_EMAIL", unique = true)
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Column(name = "USER_CATEGORY")
	public String getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", unique = true, nullable = false)	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "USER_MOBILE")
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	@Column(name = "USER_PASSWORD")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "USER_ACCOUNT_TYPE")
	public char getAccountType() {
		return accountType;
	}
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "USER_ACCOUNT_ID")
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "USER_ACT_FLAG")
	public char getActFlag() {
		return actFlag;
	}
	public void setActFlag(char actFlag) {
		this.actFlag = actFlag;
	}
	
	@Column(name = "OTP")
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	@Column(name = "OTP_EXPIRY_DATE")
	public Date getOtpExpiryDate() {
		return otpExpiryDate;
	}
	public void setOtpExpiryDate(Date otpExpiryDate) {
		this.otpExpiryDate = otpExpiryDate;
	}
	
	@OneToMany(targetEntity = UserSession.class, mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<UserSession> getUserSession() {
		return userSessions;
	}
	public void setUserSession(List<UserSession> userSessions) {
		this.userSessions = userSessions;
	}
}
