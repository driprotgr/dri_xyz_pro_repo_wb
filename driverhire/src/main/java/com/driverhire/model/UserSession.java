package com.driverhire.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SESSION")
public class UserSession implements Serializable {
	@Id
	@Column (name = "USER_ID")
	private Long userId;
	@Id
	@Column (name = "GCM_IDENTIFIER_ID")
	private String gcmIdentifierId;
	//@Id
	@Column (name = "AUTH_ID")
	private String authId;
	
	@Column (name = "CREATED_TIME")
	private Date createdTime;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getGcmIdentifierId() {
		return gcmIdentifierId;
	}
	public void setGcmIdentifierId(String gcmIdentifierId) {
		this.gcmIdentifierId = gcmIdentifierId;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
