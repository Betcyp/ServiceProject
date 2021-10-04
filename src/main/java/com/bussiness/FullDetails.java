package com.bussiness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;
import com.constants.StudentQueries;
import com.databases.DbConnection;

public class FullDetails {
	
	
	public static void insertInToDatabase(String name, int roll_no, int maths, int physics,int chemistry) throws SQLException  {
		
			Connection connection;
			DbConnection db=new DbConnection();
			connection=db.getConnection();
			PreparedStatement inserted=connection.prepareStatement(StudentQueries.INSERT_QUERY);
			try {
				inserted.setString(1,name);
				inserted.setInt(2,roll_no);
				inserted.setInt(3,maths);
				inserted.setInt(4,physics);
				inserted.setInt(5,chemistry);
				inserted.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				inserted.close();
				connection.close();
			}
	}
	public static JSONObject retrieveFromDatabase(int roll_no) throws SQLException {
		
			Connection connection;
			DbConnection db=new DbConnection();
			connection=db.getConnection();
			PreparedStatement ps=connection.prepareStatement(StudentQueries.RETRIEVE_QUERYJSON);
			ps.setInt(1,roll_no);
			ResultSet rs= ps.executeQuery();
			try {
				JSONObject jsonObject=new JSONObject();
				while(rs.next()) {
					jsonObject.put("name",rs.getString("name"));
					jsonObject.put("roll_no",rs.getInt("roll_no"));
					jsonObject.put("maths",rs.getInt("maths"));
					jsonObject.put("physics",rs.getInt("physics"));
					jsonObject.put("chemistry",rs.getInt("chemistry"));
				}
				return jsonObject;
		   }
		   catch(Exception e) {
				e.printStackTrace();
		   }
		   finally {
			   ps.close();
			   rs.close();
			   connection.close();
		   }
		   return null;
	}
}
