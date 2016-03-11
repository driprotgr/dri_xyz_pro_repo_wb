package com.driverhire.service;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import javax.transaction.Transactional;

import com.driverhire.model.User;
import com.driverhire.model.UserSession;
import com.driverhire.utils.Token;
import com.driverhire.constant.AccountType;
import com.driverhire.dao.UserDao;
import com.driverhire.dto.JsonResponse;
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
		
		//Check if user already exists
		if(newUser.getAccountId() == null || newUser.getAccountType() != AccountType.MOBILE.getValue()) {
			throw new DriverHireException(803, "Invalid request, accountId is null or Account Type in not Mobile");	
		}
		User dbUser = userDao.getValidUserByAccountId(newUser.getAccountId());
		if(dbUser != null)
		{
			throw new DriverHireException(803, "User Already Exists");
		}
		
		//delete existing invalid record
		
		dbUser = userDao.getUserByAccountId(newUser.getAccountId());
		if(dbUser != null)
		{
			boolean isDeleted = userDao.deleteUser(dbUser);
			logger.info("isDeleted:" + isDeleted);
		}
		
		
		//generate OTP
		String otp = sendOtp(newUser.getAccountId()!= null? newUser.getAccountId() : newUser.getMobile());
		newUser.setOtp(otp);
		
		
		
		newUser.setActFlag(false);
		if(newUser.getName() == null || "".equals(newUser.getName()))
				newUser.setName(newUser.getAccountId());
		if(newUser.getMobile() == null || "".equals(newUser.getMobile()))
			newUser.setMobile(newUser.getAccountId());
		dbUser = userDao.createOrUpdateUser(newUser);
		//Send SMS to user
		
		newUser = modelToDTO(dbUser);
	//	newUser.setOtp(null);  ----------------for testing disabling it
		return newUser;
  }
	

	public JsonResponse logIn(UserDto user) throws DriverHireException {
		logger.info("inside UserService.login");
		
		JsonResponse response = new JsonResponse();		// to pass the message
		
		User dbUser = userDao.getValidUserByAccountId(user.getAccountId());
		UserDto userDto = null;
//		if(user.getPassword().equals(password))
		logger.info("dbUser: " + dbUser);
		logger.info("user.getAccountType():" + user.getAccountType());
		if(user.getAccountType() == AccountType.MOBILE.getValue()) {
			
			if(dbUser == null) {
				throw new DriverHireException(802, "Account does not exist");
			}
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			logger.info("encoded Password" + passwordEncoder.encode(user.getPassword()));
			if(! passwordEncoder.matches(user.getPassword(), dbUser.getPassword()))
			{
				throw new DriverHireException(801, "Invalid login Details");
			}
		} else {
			logger.info("F or G login type");
			if(dbUser == null) {
				user.setActFlag(true);
				if(user.getCategory() == null || "".equals(user.getCategory())) {
					//throw new DriverHireException(803, "Column 'USER_CATEGORY' cannot be null");
					user.setCategory("G");
				}
				
				dbUser = userDao.createOrUpdateUser(user);
				response.setResponseMessage("NEW_USER");
			}
		}
		
		UserSession userSession = new UserSession();
		userSession.setUserId(dbUser.getUserId());
		if(user.getGcmIdentifierId() == null || "".equals(user.getGcmIdentifierId()))
			user.setGcmIdentifierId("WEB");
		userSession.setGcmIdentifierId(user.getGcmIdentifierId());
		
		String authToken = Token.generateToken(64);
		userSession.setAuthId(authToken);
		
		userSession.setCreatedTime(new Date());
		userDao.saveUserSession(userSession);
		
		userDto = modelToDTO(dbUser);
		userDto.setAuthToken(authToken);
		response.setResponseBody(userDto);
		response.setAuthToken(userDto.getAuthToken());
		return response;
		//return userDto;
	}
	
	public boolean isSeqAuthValid(String seqAuth) {
	 	UserSession userSession = userDao.getSessionssionByAuthTok(seqAuth);
	 	if(userSession == null) {
	 		return false;
	 	}
		return true;
	}
	public UserDto logIn(String mobileNo, String password, String gcmIdentifier) throws DriverHireException {
		logger.info("inside UserService.login");
		
		User user = userDao.getUserByMobileNo(mobileNo);
		if(user == null) {
			throw new DriverHireException(802, "Account does not exist");
		}
//		if(user.getPassword().equals(password))
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		logger.info("encoded Password" + passwordEncoder.encode(password));
		if(passwordEncoder.matches(password, user.getPassword()))
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
			throw new DriverHireException(801, "Invalid login Details");
		}
	}
	public UserDto confirmOtp(String accountId, String otp) throws DriverHireException{
		
		User user = userDao.getUserByAccountId(accountId);
		if(user == null) {
			throw new DriverHireException(810, "Account does not exists");
		}
		if(user.getOtp() != null &&  user.getOtp().equals(otp))
			return modelToDTO(user);
		else
			return null;
	}
	public UserDto confirmSignupOtp(UserDto user) throws DriverHireException{
		UserDto dbUser  = confirmOtp(user.getAccountId(), user.getOtp());
		 User mUser = null;
		if(dbUser != null) {
			dbUser.setActFlag(true);
			dbUser.setOtp(null);
			mUser = userDao.createOrUpdateUser(dbUser);	
			
			UserSession userSession = new UserSession();
			userSession.setUserId(mUser.getUserId());
			userSession.setGcmIdentifierId(user.getGcmIdentifierId());
			
			String authToken = Token.generateToken(64);
			userSession.setAuthId(authToken);
			
			userSession.setCreatedTime(new Date());
			userDao.saveUserSession(userSession);
			
			UserDto userDto = modelToDTO(mUser);
			userDto.setAuthToken(authToken);
		} else {
			throw new DriverHireException(805, "OTP doesnt matched");
		}
		return modelToDTO(mUser);
	}
	
	public UserDto changePassword(UserDto user) throws DriverHireException {
		if(user.getAccountType() != AccountType.MOBILE.getValue())
			throw new DriverHireException(811, "Invalid Account Type");
		UserDto dbUser;
		if(user.getAccountId() != null && "".equals(user.getAccountId()))
			dbUser = confirmOtp(user.getAccountId(), user.getOtp());
		else
			dbUser = confirmOtp(user.getMobile(), user.getOtp());
		User mUser = null;
		if(dbUser != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//logger.info("encoded Password" + passwordEncoder.encode(password));
			dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
			dbUser.setOtp(null);
			mUser = userDao.createOrUpdateUser(dbUser);
		}
		else {
			throw new DriverHireException(805, "OTP doesnt matched");
		}
		return modelToDTO(mUser);
	}
	
	public UserDto resendOtp (UserDto user) throws DriverHireException{
		User mUser ;
		if(user.getAccountId() != null && "".equals(user.getAccountId()))
			mUser = userDao.getUserByAccountId(user.getAccountId());
		else 
			mUser = userDao.getUserByAccountId(user.getMobile());
		
		if(mUser == null) {
			throw new DriverHireException(810, "Account does not exists");
		}
		String otp = sendOtp(mUser.getAccountId());
		mUser.setOtp(otp);
		userDao.createOrUpdateUser(mUser);
		return modelToDTO(mUser);
	}
	
	public UserDto updateUser(UserDto user) throws DriverHireException {
		User mUser = userDao.getUserByAccountId(user.getAccountId());
		if(mUser == null) {
			throw new DriverHireException(810, "Account does not exists");
		}
		
		mUser.setUserName(user.getName());
		mUser.setUserEmail(user.getEmail());
		mUser.setUserCategory(user.getCategory());
		mUser = userDao.createOrUpdateUser(mUser);
		return modelToDTO(mUser);
	}
	
	public String sendOtp (String mobileNo) throws DriverHireException{
		if(mobileNo == null || "".equals(mobileNo)) {
			throw new DriverHireException(810,"Mobile Number Not Valid");
		}
		String otp = Token.generateIntToken(6);
		
		return otp;
	}
	
	private UserDto modelToDTO(User user) {
		if (user == null)
			return null;
		UserDto userDto = new UserDto();
		
		userDto.setAccountId(user.getAccountId());
		userDto.setAccountType(user.getAccountType());
		if(user.getActFlag() == '0')
			userDto.setActFlag(false);
		else 
			userDto.setActFlag(true);
		userDto.setCategory(user.getUserCategory());
		userDto.setEmail(user.getUserEmail());
		userDto.setId(user.getUserId());
		userDto.setMobile(user.getUserMobile());
		userDto.setName(user.getUserName());
		
		return userDto;
	}
}