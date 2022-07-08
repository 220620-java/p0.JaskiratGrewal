package com.revature.proj0.services;
import com.revature.proj0.data.AccountDAO;
import com.revature.proj0.data.AccountPostgres;
//import com.revature.proj0.data.UserDAO;
//import com.revature.proj0.data.UserPostgres;
import com.revature.proj0.ds.List;
//import com.revature.proj0.exceptions.IdDoesNotExistException;
//import com.revature.proj0.exceptions.UsernameAlreadyExistsException;
import com.revature.proj0.models.Account;
import com.revature.proj0.models.User;

public class UserServiceImpl{
	
private AccountDAO accDao = new AccountPostgres();
	
	public Account createNewAccount(Account account) {
		account = accDao.create(account);
		
		if(account == null) {
			System.out.println("Account can't be created");
		}
		return account;
	}
	
	public Account accountDetails(Account account, User user) {
		accDao.getDetails(account, user);
		
		return account;
	}
	
	public Account deposit(Account account, double amount) {
		
		if(amount >= 0) {
			double deposit = account.getBalance() + amount;
			account.setBalance(deposit);
			accDao.updateBalance(account);
			System.out.println("Deposit Successful!");
		}
		else {
			double deposit = account.getBalance() + amount;
			account.setBalance(deposit);
			System.out.println(deposit+ "is an invalid deposit, try again with the following format: 123.45");
		}
		
		return account;
	}
	
	public Account withdraw(Account account, double amount) {
		
		if(account.getBalance() >= amount) {
			double withdraw = account.getBalance() - amount;
			account.setBalance(withdraw);
			accDao.updateBalance(account);
			System.out.println("Withdrew: "+withdraw+" ammount");
		}
		else {
			double withdraw = account.getBalance() - amount;
			account.setBalance(withdraw);
			System.out.println("not enough money in account to withdraw "+withdraw);
		}

		return account;
	}
	
	public List<Account> getAccountList(User user) {
		return accDao.allAccounts(user);
	}

	public List<Account> getMyAccounts(User user) {
		return accDao.allAccounts(user);
	}

}
//	private UserDAO userDao = new UserPostgres();
//	private AccountDAO accountDao = new AccountPostgres();
//	
//	boolean isUser = false;
//	@Override
//	public User registerUser(User user) {
//		return userDao.create(user);
//	}
//	@Override
//	public User login(String username, String passwd) {
//		User user = userDao.findByUsername(username);
//		if(user != null && passwd.equals(user.getPasswd())) {
//			isUser = true;
//			return user;
//		}
//		return null;
//	}
//	
//	@Override
//	public User makeAccount(User user) {
//		return userDao.create(user);
//	}
//	
//	@Override
//	public Account depositeFund(User user, double balance) {
//		return userDao.deposite(user, balance); 
//	}	
//	
//	@Override
//	public List<User> viewAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public List<Account> viewAllAccounts() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public User usingAccount(Account account, User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
//	
////	@Override
////	public User usingAccount(Account account, User user) {
////		// TODO Auto-generated method stub
////		return null;
//	}

	