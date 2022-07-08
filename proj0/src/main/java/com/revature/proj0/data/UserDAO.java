package com.revature.proj0.data;

//import com.revature.proj0.models.Account;
import com.revature.proj0.models.User;

public interface UserDAO extends DataAccessObject<User>{
	public User findByUsername(String username);

}
