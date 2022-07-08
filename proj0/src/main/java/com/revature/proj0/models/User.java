package com.revature.proj0.models;

import java.util.Objects;

import com.revature.proj0.ds.ArrayList;
import com.revature.proj0.ds.List;

public class User {
	private int id;
	private String username;
	private String passwd;
	private Account account;
	List<Account> accounts = new ArrayList<>();
	
	public User() {
		super();
		this.id = 0;
		this.username = "";
		this.passwd = "";
		this.setAccount(account);
	}
	
	public User(String username, String passwd) {
		super();
		this.username = username;
		this.passwd = passwd;
		//this.accounts = new ArrayList<Account>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

//	public List<Account> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(List<Account> accounts) {
//		this.accounts = accounts;
//	}
	
	//public void makeAccount(User user, Account account) {
	//}
	
	@Override
	public int hashCode() {
		return Objects.hash(username, id, passwd, account);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
//		return id == other.id && Objects.equals(passwd, other.passwd) && Objects.equals(account, other.accounts)
//				&& Objects.equals(username, other.username);
		return Objects.equals(username, other.username) && id == other.id
				&& Objects.equals(passwd, other.passwd) && Objects.equals(account, other.account);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + passwd + ", account=" + account + "]";
	}
}







//package com.revature.proj0.main;
//
//import java.util.Arrays;
//
//public class User {
//	private String username;
//	private String passwd;
//	private Double accounts[] = {0.00};
//	private static int count = 0;
//	private int id;
//	
//
//	//*****over loaded constructor_______________________
//	User(String username, String passwd){
//		if(username != null) {this.username = username;}
//		else {this.username = "username";}
//		
//		if(passwd != null) {this.passwd = passwd;}
//		else {this.passwd = passwd;}
//		
//		this.id=count++;
//	}
//
//	//getter and setters__________________________________
//	public String getUsername() {
//		return this.username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPasswd() {
//		return this.passwd;
//	}
//	public void setPasswd(String passwd) {
//		this.passwd = passwd;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public Double[] getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(Double[] accounts) {
//		this.accounts = accounts;
//	}
//	public static int getCount() {
//		return count;
//	}
//	public static void setCount(int count) {
//		User.count = count;
//	}
//	//*****no args constructor____________________________________
////	User(){
////	this.id=count++;
////	}
//	public void deposite(int index, int amount) {}
//	public void withdraw(int index, int amount) {}
//	public void newAccount() {}
//	public void getBalance(int index, int amount) {}
//	
//	public void hmAccountsMade() {System.out.println(count);}
//	
//	@Override
//	public String toString() {
//		return "User [username=" + username + ", passwd=" + passwd + ", accounts=" + Arrays.toString(accounts) + ", id="
//				+ id + ", getUsername()=" + getUsername() + ", getPasswd()=" + getPasswd() + ", getId()=" + getId()
//				+ ", getAccounts()=" + Arrays.toString(getAccounts()) + "]";
//	}
//}