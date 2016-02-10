package com.driverhire.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.driverhire.model.User;
import com.driverhire.model.UserSession;
import com.driverhire.utils.Token;
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
			/*List<UserSession> userSessions= user.getUserSession();
			for (int i = 0; i < userSessions.size(); i++) {
				if(userSessions.get(i).getGcmIdentifierId().equals(gcmIdentifier)) {
					
				}
			}*/
			
			UserSession userSession = new UserSession();
			userSession.setUserId(user.getUserId());
			userSession.setGcmIdentifierId(gcmIdentifier);
			
			String authToken = Token.generateToken(64);
			userSession.setAuthId(authToken);
			
			userSession.setCreatedTime(new Date());
			userDao.saveUserSession(userSession);
			
			UserDto userDto = modelToDTO(user);
			userDto.setAuthToken(authToken);
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
	
	public boolean isSeqAuthValid(String seqAuth) {
	 	UserSession userSession = userDao.getSessionssionByAuthTok(seqAuth);
	 	if(userSession == null) {
	 		return false;
	 	}
		return true;
	}
}