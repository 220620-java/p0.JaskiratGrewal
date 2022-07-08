package com.revature.proj0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.proj0.ds.ArrayList;
import com.revature.proj0.ds.List;
import com.revature.proj0.models.User;
import com.revature.proj0.models.Account;
import com.revature.proj0.utils.ConnectionUtil;


public class AccountPostgres implements AccountDAO{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Account create(Account account) {
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into accounts "
							+"(account_id, balance, user_id)"
							+"values (default, ?, ?)";
			
			String[] keys = {"account_id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setDouble(1, account.getBalance());
			stmt.setInt(2, account.getUserId());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			if (resultSet.next() && rowsAffected==1) {
				account.setId(resultSet.getInt("account_id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	

//	@Override
//	public List<Account> findById(int id) {
//		Account account = null;
//		
//		try(Connection conn = connUtil.getConnection()){
//			String sql = "select account.id, "
//						+ "balance, "
//						+ "user.username as username, "
//						+ "from account "
//						+ "join users on account.id = user.accounts.id"
//						+ "where account.id = ?";
//			
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			
//			stmt.setInt(1, id);
//			
//			ResultSet resultSet = stmt.executeQuery();
//			
//			if(resultSet.next()) {
//				double balance = resultSet.getDouble("balance");
//				account = new Account(balance);
//				account.setId(id);
//			} else{
//				conn.rollback();
//				return null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return (List<Account>) account;
//	}

	@Override
	public List<Account> allAccounts(){
		List<Account> allAccounts = new ArrayList<>();
		try(Connection conn = connUtil.getConnection()){
			String sql = "SELECT * FROM accounts where user_id = ? ";
//			String sql = "SELECT account.id, "
//							+"account.balance, "
//							+"join users on accounts.account.id = account.id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next()){
				int id = resultSet.getInt("user_id");
				Double balance = resultSet.getDouble("balance");
				
				Account account = new Account(balance);
				
				allAccounts.add(account);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return allAccounts;
	}
	
	@Override
	public void updateBalance(Account account) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "UPDATE accounts "
							+"set balance = ?,"
							+"where account_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setInt(2, account.getAccountId());
			

			int rowsAffected = stmt.executeUpdate();
			
			if(rowsAffected <=1) {
				conn.commit();
			}else{
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Account account) {
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "delete from accounts where account_id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, account.getId());
			
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected<=1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Account account) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update accounts "
							+ "balance = ?,"
							+ "where account_id = ?";
				
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, account.getBalance());
			stmt.setInt(2, account.getAccountId());
						
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


//	@Override
//	public List<Account> allAccounts(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public Account getDetails(Account account, User user) {
		try (Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				int accountId = resultSet.getInt("account_id");
				double accountBalance = resultSet.getDouble("balance");
				
				account.setAccountId(accountId);
				account.setBalance(accountBalance);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}


	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Account> allAccounts(User user) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<Account> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
