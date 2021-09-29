package com.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.constants.StudentQueries;
import com.databases.DbConnection;
import com.services.FirstService;

public class FullDetails {
	
	
	public static void insertInToDatabase(String name, int maths, int physics,int chemistry)  {
		
	
		try {
			Connection connection;
			DbConnection db=new DbConnection();
			connection=db.getConnection();
	
			PreparedStatement inserted=connection.prepareStatement(StudentQueries.INSERT_QUERY);
			inserted.setString(1,name);
			inserted.setInt(2,maths);
			inserted.setInt(3,physics);
			inserted.setInt(4,chemistry);
			inserted.executeUpdate();
	
	    }
		catch(Exception e) {
			e.printStackTrace();
		}
}
}
