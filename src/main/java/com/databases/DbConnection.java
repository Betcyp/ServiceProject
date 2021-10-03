package com.databases;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnection {
	 Connection connection;
	 private static InitialContext context;
	 private static DataSource datasource;
	
	public static void initTomcatDatasource(){
		try {
			context=new InitialContext();
			datasource=(DataSource) context.lookup("java:/comp/env/jdbc/StudentDB");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public Connection getConnection() throws SQLException{
		
			connection=datasource.getConnection();
			return connection;
		
	}
}
	
	


