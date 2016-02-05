package com.driverhire.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.driverhire.model.User;
import com.driverhire.dao.UserDao;
import com.driverhire.dto.UserDto;
import com.driverhire.exception.DriverHireException;

@Service
@Transactional
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	public UserDto createUser(UserDto newUser) throws DriverHireException {
		logger.info("inside UserService.createUser");
		return newUser;
  }
	
	public UserDto logIn(String mobileNo, String password, String gcmIdentifier) throws DriverHireException {
		logger.info("inside UserService.login");
		
		User user = userDao.getUserByMobileNo(mobileNo);
		if(user == null) {
			throw new DriverHireException("802", "Account does not exist");
		}
		if(user.getPassword().equals(password))
		{
			UserDto userDto = modelToDTO(user);
			return userDto;
		} else {
			throw new DriverHireException("801", "Invalid login Details");
		}
	}
	
	private UserDto modelToDTO(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setAccountId(user.getAccountId());
		userDto.setAccountType(user.getAccountType());
		userDto.setActFlag(user.getActFlag());
		userDto.setUserCategory(user.getUserCategory());
		userDto.setUserEmail(user.getUserEmail());
		userDto.setUserId(user.getUserId());
		userDto.setUserMobile(user.getUserMobile());
		userDto.setUserName(user.getUserName());
		
		return userDto;
	}
}