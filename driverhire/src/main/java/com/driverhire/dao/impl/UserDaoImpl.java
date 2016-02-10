package com.driverhire.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.UserException;
import org.springframework.stereotype.Repository;

import com.driverhire.controller.mobile.UserController;
import com.driverhire.dao.UserDao;
import com.driverhire.dto.UserDto;
import com.driverhire.model.User;
import com.driverhire.model.UserSession;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	public User createUser(UserDto userDto) {
		User user = new User();
		user.setAccountId(userDto.getAccountId());
		user.setAccountType(userDto.getAccountType());
		user.setActFlag('N');
		user.setCreatedDate(new Date());
		user.setOtp(userDto.getOtp());
		user.setOtpExpiryDate(new Date());
		user.setPassword(user.getPassword());
		user.setUserCategory(userDto.getUserCategory());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserMobile(userDto.getUserMobile());
		user.setUserName(userDto.getUserName());
		
		return user;
	}


	public User getUserByMobileNo(String mobileNo) {
		User user;
		try {
			user = (User) getSession().createCriteria(User.class)
		        .add(Restrictions.and(Restrictions.eq("userMobile", mobileNo))).list().get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public UserSession saveUserSession (UserSession userSession) {
		saveOrUpdateObject(userSession);
		return userSession;
	}



	public UserSession getSessionssionByAuthTok(String authToken) {
		UserSession userSession = null;
		try {
			userSession = (UserSession) getSession().createCriteria(UserSession.class)
				.add(Restrictions.and(Restrictions.eq("authId", authToken))).list().get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userSession;
	}
}
