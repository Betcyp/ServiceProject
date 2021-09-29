package com.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException; 
public class ExampleLog {
	static Logger log = Logger.getLogger(ExampleLog.class);  
    
	   public static void main(String[] args)throws IOException,SQLException{ 
	       PropertyConfigurator.configure("src/main/webapp/WEB-INF/log4j.properties");
	       log.debug("Log4j configuration is successfull !!");
	   }  
}  