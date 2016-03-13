package com.driverhire.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.driverhire.dao.UserDao;
import com.driverhire.model.User;
import com.driverhire.model.UserSession;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
	

	
	public User createOrUpdateUser(User user){
		saveOrUpdateObject(user);
		return user;	
	}
	public User updateUser(User user){
		updateObject(user);
		return user;	
	}
	public boolean deleteUser(User user) {
		getSession().delete(user);
//		getSession().flush();
		return true;
	}
	public User getUserByMobileNo(String mobileNo) {
		User user;
		try {
			user = (User) getSession().createCriteria(User.class)
		        .add(Restrictions.and(Restrictions.eq("userMobile", mobileNo))).list().get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace();
			return null;
		}
		return user;
	}
	public User getValidUserByAccountId(String accountId) {
		User user;
		try {
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("accountId", accountId));
			criteria.add(Restrictions.eq("actFlag", 'Y'));
			user = (User) criteria.list().get(0);
		        
		} catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace();
			return null;
		}
		return user;
	}
	public User getUserByAccountId(String accountId) {
		User user;
		try {
			Criteria criteria = getSession().createCriteria(User.class);
			criteria.add(Restrictions.eq("accountId", accountId));
			//criteria.add(Restrictions.eq("actFlag", 'Y'));
			user = (User) criteria.list().get(0);
		        
		} catch (Exception e) {
			logger.error(e.getMessage());
			//e.printStackTrace();
			return null;
		}
		return user;
	}
	public UserSession saveUserSession (UserSession userSession) {
		saveOrUpdateObject(userSession);
		return userSession;
	}

	public UserSession updateUserSession(UserSession userSession) {
		updateObject(userSession);
		return userSession;
	}

	public UserSession getSessionssion(String authToken) {
		UserSession userSession = null;
		try {
			userSession = (UserSession) getSession().createCriteria(UserSession.class)
				.add(Restrictions.and(Restrictions.eq("authId", authToken))).list().get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userSession;
	}
	public UserSession getSessionssion(long userId, String gcmIdentifier) {
		UserSession userSession = null;
		try {			
			Criteria criteria = getSession().createCriteria(UserSession.class);
			criteria.add(Restrictions.eq("gcmIdentifierId", gcmIdentifier));
			criteria.add(Restrictions.eq("userId", userId));
			userSession = (UserSession)criteria.list().get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return userSession;
	}
		
/*	public User createOrUpdateUser(UserDto userDto){
		//getHibernateTemplate().flush();
		User user = new User();
		user.setAccountId(userDto.getAccountId());
		user.setAccountType(userDto.getAccountType());
		user.setUserId(userDto.getId());
		if (userDto.isActFlag())
			user.setActFlag('Y');
		else 
			user.setActFlag('N');
		user.setCreatedDate(new Date());
		user.setOtp(userDto.getOtp());
		user.setOtpExpiryDate(new Date());
		user.setPassword(user.getPassword());
		if (userDto.getCategory() == null || "".equals(userDto.getCategory())) 
			userDto.setCategory("G");
		user.setUserCategory(userDto.getCategory());
		user.setUserEmail(userDto.getEmail());
		user.setUserMobile(userDto.getMobile());
		user.setUserName(userDto.getName());
		saveOrUpdateObject(user);
		return user;
	}
	public User updateUser(UserDto userDto) {
		User user = new User();
		user.setAccountId(userDto.getAccountId());
		user.setAccountType(userDto.getAccountType());
		user.setUserId(userDto.getId());
		if (userDto.isActFlag())
			user.setActFlag('Y');
		else 
			user.setActFlag('N');
		user.setCreatedDate(new Date());
		user.setOtp(userDto.getOtp());
		user.setOtpExpiryDate(new Date());
		user.setPassword(user.getPassword());
		if (userDto.getCategory() == null || "".equals(userDto.getCategory())) 
			userDto.setCategory("G");
		user.setUserCategory(userDto.getCategory());
		user.setUserEmail(userDto.getEmail());
		user.setUserMobile(userDto.getMobile());
		user.setUserName(userDto.getName());
		updateObject(user);
		return user;
	}
	*/
}
