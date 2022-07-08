package com.revature.proj0.data;

import com.revature.proj0.ds.List;
import com.revature.proj0.models.User;
import com.revature.proj0.models.Account;

public interface AccountDAO extends DataAccessObject<Account>{
	public List<Account> allAccounts(User user);
	public Account getDetails(Account account, User user);
	public void updateBalance(Account account);
	List<Account> allAccounts();
}
