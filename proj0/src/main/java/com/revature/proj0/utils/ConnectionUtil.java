package com.revature.proj0.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil connUtil;
	private Properties props;
	
	private ConnectionUtil() {
		props = new Properties();
		
		InputStream propsFile = ConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		try {
			props.load(propsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (connUtil == null) {
			connUtil = new ConnectionUtil();
		}
		return connUtil;
	}
	
	// factory: creates Connection objects and returns them
	public Connection getConnection() {
		// when connecting to the DB, we need:
		// JDBC driver
		// database URL
		// username
		// password
		Connection conn = null;
		
		// using environment variables
//		String dbUrl = System.getenv("DB_URL");
//		String dbUser = System.getenv("DB_USER");
//		String dbPass = System.getenv("DB_PASS");
		
		// using properties file
		String dbUrl = props.getProperty("url");
		String dbUser = props.getProperty("usr");
		String dbPass = props.getProperty("psw");
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					dbUrl,
					dbUser,
					dbPass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}


//package com.revature.proj0.utils;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//// singleton design patter: 1 instance of something
////factory design patter: generates and returns a object
//public class ConnectionUtil {
//	private static ConnectionUtil connUtil;
//	private Properties props;
//	
//	private ConnectionUtil() {
//		props = new Properties();
//		InputStream propsFile = ConnectionUtil.class.getClassLoader()
//				.getResourceAsStream("database.properties");
//		try{
//			props.load(propsFile);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static synchronized ConnectionUtil getConnectionUtil() {
//		if(connUtil == null) {
//			connUtil = new ConnectionUtil();
//		}
//		return connUtil;
//	}
//	public Connection getConnection() {
//		Connection conn = null;
//		
//		//optional way:using environment variables
//		String dbUrl = System.getenv("jdbc:postgresql://database-3.cg8npnu3ua5y.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=proj0");
//		String dbUser = System.getenv("postgres");
//		String dbPass = System.getenv("Doorway12!");
//		
//		String dbUrl = props.getProperty("url");
//		String dbUser = props.getProperty("usr");
//		String dbPass = props.getProperty("psw");
//		
//		try {
//			try {
//				Class.forName("org.postgresql.Driver");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			conn = DriverManager.getConnection(
//					//"jdbc:postgresql://database-3.cg8npnu3ua5y.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=proj0",
//					"dbUrl",
//					"dbUser",
//					"dbPass"
//					);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//}