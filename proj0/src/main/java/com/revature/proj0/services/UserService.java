package com.revature.proj0.services;

import com.revature.proj0.data.UserDAO;
import com.revature.proj0.data.UserPostgres;
import com.revature.proj0.ds.List;
import com.revature.proj0.exceptions.UsernameAlreadyExistsException;
import com.revature.proj0.models.Account;
import com.revature.proj0.models.User;

public class UserService {
	private UserDAO userDao = new UserPostgres();
	
	public User registerUser(User user) throws UsernameAlreadyExistsException{
		user = userDao.create(user);
		
		if(user == null) {
			throw new UsernameAlreadyExistsException();
		}
		return user;
	}

	public User login(String username, String password) {
		User user = userDao.findByUsername(username);
		
		if(user != null && (password != null && password.equals(user.getPasswd()))) {
			return user;
		}
		else {
			return null;
		}
	}


}


//public interface UserService {
//
//	//public User registerUser(User user) throws UserNameAlreadyExistsException;
//	
//	public User registerUser(User user);
//	
//	public User login(String username, String password);
//	
//	public List<User> viewAllUsers();
//	
//	public List<Account> viewAllAccounts();
//	
//	public User makeAccount(User user);
//	
//	public User usingAccount(Account account, User user);
//	
//	public User depositeFund(User user, double balance);
//	
//	//public User makeUser(User user) throws UsernameAlreadyExistsException;
//	
//	
//}
