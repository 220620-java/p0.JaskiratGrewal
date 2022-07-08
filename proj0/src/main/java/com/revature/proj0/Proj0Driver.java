package com.revature.proj0;

import java.util.Scanner;

import com.revature.proj0.data.AccountPostgres;
import com.revature.proj0.ds.List;
import com.revature.proj0.exceptions.UsernameAlreadyExistsException;
import com.revature.proj0.models.Account;
import com.revature.proj0.models.User;
import com.revature.proj0.services.UserService;
import com.revature.proj0.services.UserServiceImpl;

public class Proj0Driver {
	
	public static User user = new User();
	public static Account account = new Account();
	
	private static Scanner scanner = new Scanner(System.in);
	private static UserService userService = new UserService();
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();

	public static void main (String[] args) {
		boolean usingProj0 = true;
		
		User user = null;
		while (usingProj0) {
			if (user == null) {
				System.out.println(
						"1. Log in\n" +
						"2. Register\n" +
						"3. close app");
				
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					user = logIn();
					break;
				case "2":
					register();
					break;
				default:
					usingProj0 = false;
					System.out.println("app shut down");
				}
			}
			
			if (user != null) {
				System.out.println("1. View Account"
						+ "2. Deposit"
						+ "3. withdraw"
						+ "4. View all accounts"
						+ "5. Make new account\n"
						+ "x. Log out");
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
					accountDetail(account, user);
					break;
				case "2":
					deposite(account, user);
					break;
				case "3":
					withdraw(account, user);
					break;
				case "4":
					viewMyAccounts(user);
					break;
				case "5":
					MakeNewAccount(user);
					break;
				default:
					System.out.println("Logging out.");
					user = null;
				}
			}
		}
		scanner.close();
	}
	
	private static User logIn() {
		boolean loggingIn = true;
		
		while (loggingIn) {
			System.out.println("Enter your username: ");
			String username = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			
			User user = userService.login(username, password);
			
			if (user==null) {
				System.out.println("Couldn't find a user");
				loggingIn = false;
			} else {
				return user;
			}
		}
		return null;
	}

	private static void register() {
		boolean registering = true;
		
		while (registering) {
			System.out.println("Enter a username: ");
			String username = scanner.nextLine();
			System.out.println("Enter a password: ");
			String password = scanner.nextLine();
			
			System.out.println("Type \"y\" to confirm, \"n\" to try again, or something "
					+ "else to go back.");
			String input = scanner.nextLine().toLowerCase();
			
			switch (input) {
			case "y":
				Account acc = new Account(0.00);
				User user = new User(username, password);
				try {
					userService.registerUser(user);
					account.setId(user.getId());
					userServiceImpl.createNewAccount(account);
					
					registering = false;
					System.out.println("regristered account");
				} catch (UsernameAlreadyExistsException e) {
					System.out.println("User with that username already exists.");
				}
				break;
			case "n":
				System.out.println("try again.");
				break;
			default:
				System.out.println("going back.");
				registering = false;
			}
		}
	}
	
	private static User MakeNewAccount(User user) {
		boolean makingAccount = true;
		
		while(makingAccount) {
			System.out.println("how much would you like to initially deposite");
			System.out.println("format the following way: (123.45)");
			double initial = scanner.nextDouble();
			scanner.nextLine();
			
			Account newAccount = new Account(initial);
			newAccount.setId(user.getId());
			userServiceImpl.createNewAccount(newAccount);
			
			System.out.println("Made new account with the balance of: "+ initial);
	
			makingAccount = false;
		}
		return null;
	}
	private static Account accountDetail(Account account, User user) {
		userServiceImpl.accountDetails(account, user);
		System.out.println("User ID: "+ user.getId());
		System.out.println("Account ID: "+ account.getId());
		System.out.println("balance: "+ account.getBalance());
		return account;
	}
	
	private static Account deposite(Account account, User user) {
		userServiceImpl.accountDetails(account, user);
		System.out.println("Enter deposit ammount: ");
		System.out.println("format the following way: (123.45)");
		double depositSum = scanner.nextDouble();
		System.out.println("deposited: "+depositSum+". new balance is now: "+account.getBalance());
		return account;
	}
	
	private static Account withdraw(Account account, User user) {
		userServiceImpl.accountDetails(account, user);
		System.out.println("Enter withraw ammount: ");
		System.out.println("format the following way: (123.45)");
		double withdrawSum = scanner.nextDouble();
		System.out.println("withdrew: "+withdrawSum+"new balance is now: "+account.getBalance());
		return account;
	}
	
	private static User viewMyAccounts(User user) {
		List<Account> accounts = userServiceImpl.getMyAccounts(user);
		
		System.out.println(user.getUsername()+"'s Accounts:");
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println("Account id: "+ accounts.get(i).getAccountId());
			System.out.println("Account id: "+ accounts.get(i).getBalance());
		}
		return user;
	}
}

//package com.revature.proj0.main;
////import java.util.Arrays;
//import java.util.Scanner;
//import java.util.*;
//
//public class proj0Main {
//
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		int firstSelection;
//		objectClass();
//		boolean isLoggedIn = false;
//		String loggedInUser;
//		//User myUser = new User("user1", "pass1");
//	    while (true) {
//	        System.out.println("1 - login");
//	        System.out.println("2 - regrister");
//	        firstSelection = scanner.nextInt();
//	        if (firstSelection == 1){
//	        	firstSelection = 1;
//	        }
//	        if (firstSelection == 2){
//	        	firstSelection = 2;
//	        }
//	        if (firstSelection == 1 || firstSelection == 2)
//	            break;
//	        else {
//	            System.out.println(firstSelection + " is not a valid entry");
//	            System.out.println("---------------------");
//	            continue;
//	        }
//	    }
//	    if (firstSelection == 1) {
//	    	System.out.println(firstSelection+" login");
//	    	loginUser();
//	    }
//	    if (firstSelection == 2) {
//	    	System.out.println(firstSelection+" regrister");
//	    	regUser();
//	    }
//	    
//	//_________________________________________________________________}
//	   scanner.close();
//	}
//	public static void loginUser() {
//		
//  	System.out.println("Username:");
//		Scanner scanner = new Scanner(System.in);
//		String usernamelog = scanner.nextLine();
//  	System.out.println("Password:");
//  	String passwdlog = scanner.nextLine();
//  	System.out.println("Username: "+usernamelog+" Password "+passwdlog);
//	}
//	
//	public static void regUser() {
//		User[] users = new User[10];
//		
//  	System.out.println("Create username:");
//		Scanner scanner = new Scanner(System.in);
//		String usernamelog = scanner.nextLine();
//  	System.out.println("create password:");
//  	String passwdlog = scanner.nextLine();
//  	System.out.println("created new User");
//  	System.out.println("Username: "+usernamelog+" Password "+passwdlog);
//  	
//  	User newUser = new User(usernamelog, passwdlog);
//  	int index = newUser.getId()-1;
//  	users[index] = newUser;
//  	String loggedInUser = usernamelog;
//  	System.out.println(loggedInUser+" is now logged in");
//  	System.out.println(Arrays.toString(users));
//	}
//	
//  private static void objectClass() {
//  	User myUser = new User("","");
//  }
//  private static void project0() {
//  	ArrayList<User> users = null;
//  }
//}