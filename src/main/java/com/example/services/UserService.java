package com.example.services;

import java.sql.SQLException;

import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UserNameAlreadyExistsException;
import com.example.logging.Logging;
import com.example.models.User;
import com.example.models.UserRole;

public class UserService {
	private UserDao uDao;
	
	public UserService() {
		
	}
	public UserService(UserDao udao) {
		this.uDao = udao;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		User u = uDao.selectUserByUsername(username);
		if(u.getId() == 0) {
			Logging.logger.warn("User tried logging in that does not exist");
			u=null;
			return u;
		}
		if(!u.getPassword().equals(password)) {
			Logging.logger.warn("User tried to login with invalid credentials");
			u=null;
			return u;
		}
		else {
			Logging.logger.info("User was logged in");
			return u;
		}
	}
	
	public User signUp(String first, String last, String email, String password) throws UserNameAlreadyExistsException, SQLException{
		
		UserRole role = new UserRole(1, "EMPLOYEE");
		
		User u = new User(first, last, email, password, role);
		
		User newUser = uDao.selectUserByEmail(u.getEmail());	
		
		if (newUser == null) {
			uDao.insert(u);
			newUser = uDao.selectUserByUsername(u.getUsername());
			Logging.logger.info("New user has registered");
		} else {
			Logging.logger.info("User already exists with current email");
		}
		
		return newUser;
	}
}
