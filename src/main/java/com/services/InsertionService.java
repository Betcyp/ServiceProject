package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.bussiness.FullDetails;

public class InsertionService extends HttpServlet {
	static Logger log = Logger.getLogger(InsertionService.class);
	private static final long serialVersionUID = 1L; 
    
   public InsertionService() {
        super();
        }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {
	   
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      sb.append(line);
		} 
		catch (Exception e) { 
			  e.printStackTrace();
		}

		log.info("Request JSON string is:" +sb.toString());
		
		JSONObject jsonObject=new JSONObject(sb.toString());
		log.info(jsonObject);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			String name=(String) jsonObject.get("Name");
			int roll_no=(int) jsonObject.get("Roll_no");
			int maths=(int) jsonObject.get("Maths");
			int physics=(int) jsonObject.get("Physics");
			int chemistry=(int) jsonObject.get("Chemistry");
				
			FullDetails.insertInToDatabase(name,roll_no,maths,physics,chemistry);
			out.print("{\"status\":\"success\"}");
		}
		catch(Exception e) {
			out.print("{\"status\":\"failed\"}");
		}
}
}
	
	
	
