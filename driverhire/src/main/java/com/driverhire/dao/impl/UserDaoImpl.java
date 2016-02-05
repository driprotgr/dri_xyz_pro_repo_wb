package com.driverhire.dao.impl;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.driverhire.dao.UserDao;
import com.driverhire.dto.UserDto;
import com.driverhire.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {


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
		User user = (User) getSession().createCriteria(User.class)
		.add(Restrictions.and(Restrictions.eq("USER_MOBILE", mobileNo)));

		/*CrewMaster crewMaster = (CrewMaster) getSession().createCriteria(CrewMaster.class).add(Restrictions.and(
                    Restrictions.eq("userName", crewMasterDTO.getUserName()),
                    Restrictions.eq("password", crewMasterDTO.getPassword()))).uniqueResult();
        */
		return user;
	}
}
