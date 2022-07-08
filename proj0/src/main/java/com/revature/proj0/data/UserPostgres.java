package com.revature.proj0.data;

import com.revature.proj0.ds.List;
import com.revature.proj0.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

//import com.revature.proj0.ds.ArrayList;
import com.revature.proj0.utils.ConnectionUtil;

//User create(User user)
//User findById(int id)
//update(User user)
//delete(User user)
//findByUsername(String username) //<----optional but shouldnt take in username


public class UserPostgres implements UserDAO{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public User create(User user) {
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into users "
							+"(id, username, passwd)"
							+"values (default, ?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPasswd());
			//stmt.setList<Account>(4, user.getAccounts());
			
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected==1) {
				user.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User findById(int id) {
		User user = null;
		
		try(Connection conn = connUtil.getConnection()){
			String sql = "select id, "
							+"user.username, "
							+"accounts.account_id as account_id, "
							+"accounts.balance as account.balance, "
							+"from user "
							+"join accounts on accounts.accounts_id = accounts.account_id "
							+"where user.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				String username = resultSet.getString("username");
				String passwd = resultSet.getString("passwd");
				//List<Account> accounts = resultSet.getList<Account>("accounts");
				user = new User(username, passwd);
				user.setId(id);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
//	@Override
//	public List<User> findAll() {
//		List<User> allUsers = new ArrayList<>();
//		try (Connection conn = connUtil.getConnection()){
//			String sql = "select user.id, "
//							+"username, "
//							+"account.id as account_id, "
//							+"account.balance as account_balance, "
//							+"from user "
//							+ "join account on accounts.id = account.id";
//			Statement stmt = conn.createStatement();
//			ResultSet resultSet = stmt.executeQuery(sql);
//			
//			while(resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String username = resultSet.getString("username");
//				String passwd = resultSet.getString("passwd");
//				
//				User user = new User(username, passwd);
//				user.setId(id);
//				allUsers.add(user);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return allUsers;
//	}

	@Override
	public void update(User user) {
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "update users "
							+"set username=?,"
							+"set passwd = ?,"
							+"where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPasswd());
			stmt.setInt(3, user.getId());
			
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected<=1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(User user) {
		try(Connection conn = connUtil.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete From users where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user.getId());
			
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected<=1) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findByUsername(String username) {
		User user = new User();
		try (Connection conn = connUtil.getConnection()){
			
			String sql = "SELECT * FROM users WHERE username = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()) {
				user.setId(resultSet.getInt("user_id"));
				user.setUsername(resultSet.getString("username"));
				user.setPasswd(resultSet.getString("passwrd"));
			}
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public User findByUsername(String username) {
//		User user = null;
//		
//		try(Connection conn = connUtil.getConnection()){
//			String sql = "select user.username, "
//							+"user.id, "
//							+"account.id as account.id, "
//							+"account.balance as account.balance, "
//							+"from user "
//							+"join account on accounts.id = account.id "
//							+"where user.username = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			ResultSet resultSet = stmt.executeQuery();
//			
//			if(resultSet.next()) {
//				Integer id = resultSet.getInt("id");
//				String passwd = resultSet.getString("passwd");
//				//List<Account> accounts = resultSet.getList<Account>("accounts");
//				user = new User(id, passwd);
//				user.setUsername(user);
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}

}
