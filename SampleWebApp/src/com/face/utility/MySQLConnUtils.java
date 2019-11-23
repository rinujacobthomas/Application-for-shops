package com.face.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQLConnUtils {
											//  JDBC connection class

		 static Connection conn= null; 
		 public static Properties loadPropertiesFiles() throws IOException
		 {
			 Properties prop = new Properties();			// properties object
			 InputStream in = new FileInputStream("C:\\Users\\Rinu Thomas\\eclipse-workspace\\SampleWebApp\\resources\\jdbc.properties");
			 										// object of FileInputStream with file location
			 prop.load(in);
			 in.close();								//object closing
			return prop;
		 }
		 public static Connection getConnection() throws Exception
		 {
			 System.out.println("getconnection in MySQLConnUtils");
			 Properties prop = loadPropertiesFiles();								//loading properties
			 final String driver = prop.getProperty("driver");								//storing to final variables
			 final String url = prop.getProperty("url");
			 final String username = prop.getProperty("username");
			 final String password = prop.getProperty("password");
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			System.out.println("connected to database");
			return conn;														//returning connection object
		 }
		
				 
	}