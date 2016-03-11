package com.driverhire.dao;

import com.driverhire.dto.UserDto;

import com.driverhire.model.User;
import com.driverhire.model.UserSession;

public interface UserDao {
	
	public User createOrUpdateUser(UserDto user);
	public User createOrUpdateUser(User user);
	public User getUserByMobileNo(String mobileNo);
	public UserSession saveUserSession (UserSession userSession) ;
	public UserSession getSessionssionByAuthTok (String authToken) ;
	public User getUserByAccountId(String accountId);
	public User getValidUserByAccountId(String accountId);
	public boolean deleteUser(User user);
}
