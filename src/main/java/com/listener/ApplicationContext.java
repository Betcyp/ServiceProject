package com.listener;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.databases.DbConnection;
import com.tests.ExampleLog;

public class ApplicationContext implements ServletContextListener {
	static Logger log = Logger.getLogger(ApplicationContext.class);  

	public void contextInitialized(ServletContextEvent sce) {
		log.info("context initialization started");
		
		ServletContext context = sce.getServletContext();
		String log4jFile = context.getInitParameter("log4jFilelocation");
		String log4jFilePath = context.getRealPath("") + File.separator + log4jFile;
		PropertyConfigurator.configure(log4jFilePath);
	
		log.info("log4j file is loaded successfully");
		
		  
		try {
			DbConnection.initTomcatDatasource();
			DbConnection db=new DbConnection();
			
			Connection connection=db.getConnection();
			log.debug("Dbconnection");
		
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	    log.info("context initialization ended");
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		
		log.info("context object destroyed");
	}
}
