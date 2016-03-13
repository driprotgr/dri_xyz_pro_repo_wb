package com.driverhire.dao;

import com.driverhire.model.User;
import com.driverhire.model.UserSession;

public interface UserDao {
	//public User createUser(User user);
	//public User createOrUpdateUser(UserDto user);
	public User createOrUpdateUser(User user);
	//public User updateUser(UserDto userDto);
	public User updateUser(User user);
	public User getUserByMobileNo(String mobileNo);
	
	public User getUserByAccountId(String accountId);
	public User getValidUserByAccountId(String accountId);
	public boolean deleteUser(User user);
	
	public UserSession getSessionssion (String authToken) ;
	public UserSession getSessionssion(long userId, String gcmIdentifier) ;
	public UserSession saveUserSession (UserSession userSession) ;
	public UserSession updateUserSession(UserSession userSession);

}
