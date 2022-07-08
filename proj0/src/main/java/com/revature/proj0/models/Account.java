package com.revature.proj0.models;

import java.util.Objects;

public class Account {
	private int userId = -1;
	private double balance;
	private int accountId = -1;
	private double initial;
	
	public Account(int userid) {
		setId(userId);
	}
	
	public Account(String accountType, double deposit) {
		this.initial = deposit;
		this.balance = initial;
	}
	
	public Account() {
		//super();
		this.initial = 0.00;
		this.balance = 0.00;
	}
	
	public Account(double balance) {
		super();
		this.userId = 0;
		this.balance = balance;
	}
	
	public Account(int accountID, double deposit) {
		this.accountId = accountID;
		this.initial = deposit;
		this.balance = initial;
	}

	public int getId() {
		return accountId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setId(int userId) {
		this.userId = userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setInitial(double initial) {
		this.initial = initial;
	}
	
	public double getInitial() {
		return initial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, balance, initial, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		//return balance == other.balance &&  id == other.id;
		return accountId == other.accountId && Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& Double.doubleToLongBits(initial) == Double.doubleToLongBits(other.initial)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		//return "Account [id=" + id + ", balance=" + balance +"]";
		return "Account [userId=" + userId + ", accountId=" + accountId
				+ ", initial=" + initial + ", balance=" + balance + "]";
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}

