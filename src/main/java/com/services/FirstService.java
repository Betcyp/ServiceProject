package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.HTTP;
import org.json.JSONObject;

import com.bussiness.FullDetails;

/**
 * Servlet implementation class FirstService
 */
@WebServlet("/FirstService")
public class FirstService extends HttpServlet {
	static Logger log = Logger.getLogger(FirstService.class);

	private static final long serialVersionUID = 1L;
	//public static final String HTML_START="<html><body>";
	//public static final String HTML_END="</body></html>";   
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
		
		StringBuffer sb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      sb.append(line);
		  } 
		  catch (Exception e) { 
			  e.printStackTrace();
		  }

		log.info(sb.toString());
		
		try {
			log.info("again");
			JSONObject jsonObject=HTTP.toJSONObject(sb.toString());
			//log.debug(jsonObject);)
			//log.info("name" +jsonObject.getString("name"));
			String name=jsonObject.getString("name");
			int maths=jsonObject.getInt("maths");
			int physics=jsonObject.getInt("physics");
			int chemistry=jsonObject.getInt("chemistry");
			log.info(name);
			FullDetails.insertInToDatabase(name, maths, physics, chemistry);
			log.debug("testing");
		}
		catch(Exception e) {
			//e.printStackTrace();
			log.error(e);
		}
		
		log.debug("just testing");
		
		
}
}	
	
	
