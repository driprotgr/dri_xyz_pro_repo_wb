package com.driverhire.dao;

import com.driverhire.dto.UserDto;
import com.driverhire.model.User;

public interface UserDao {
	
	public User createUser(UserDto user);
	public User getUserByMobileNo(String mobileNo);
}
