package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.bussiness.FullDetails;
import com.bussiness.Students;


/**
 * Servlet implementation class FirstService
 */
@WebServlet("/FirstService")
public class FirstService extends HttpServlet {
	static Logger log = Logger.getLogger(FirstService.class);
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
   public FirstService() {
        super();
        }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException   {
		/*Students stud = new Students("Meera", 18, 76, 78,75);
		String studentJsonString = this.gson.toJson(stud);
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(studentJsonString);
        out.flush(); */  
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
		
		try {
			JSONObject jsonObject=new JSONObject(sb.toString());
			log.info(jsonObject);
			//jsonObject.put();
			/*log.info(jsonObject.get("Name"));
			log.info(jsonObject.get("Roll_no"));
			log.info(jsonObject.get("Maths"));
			log.info(jsonObject.get("Physics"));
			log.info(jsonObject.get("Chemistry"));*/
		
			String name=(String) jsonObject.get("Name");
			int roll_no=(int) jsonObject.get("Roll_no");
			int maths=(int) jsonObject.get("Maths");
			int physics=(int) jsonObject.get("Physics");
			int chemistry=(int) jsonObject.get("Chemistry");
			
			try {
			
				FullDetails.insertInToDatabase(name,roll_no,maths,physics,chemistry);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String outputString="{\"status\":\"success\"}";
				out.print(outputString);
			
			}
			
			catch(Exception e) {
				throw new SQLException("{\"status\":\"failed\"}");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
}
}	
	
	
