package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.bussiness.FullDetails;


public class RetrieveService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(RetrieveService.class);
       
    public RetrieveService() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try {
			int roll_no=(int) jsonObject.get("Roll_no");
			JSONObject obj=FullDetails.retrieveFromDatabase(roll_no);
			JSONObject obj2=new JSONObject();
			obj2.put("result",obj);
			out.print(obj2.toString());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}


