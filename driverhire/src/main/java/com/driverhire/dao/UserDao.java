package com.driverhire.dao;

import com.driverhire.dto.UserDto;
import com.driverhire.model.User;
import com.driverhire.model.UserSession;

public interface UserDao {
	
	public User createUser(UserDto user);
	public User getUserByMobileNo(String mobileNo);
	public UserSession saveUserSession (UserSession userSession) ;
	public UserSession getSessionssionByAuthTok (String authToken) ;
}
