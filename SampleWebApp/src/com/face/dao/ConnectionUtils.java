package com.face.dao;

import java.sql.Connection;

import com.face.utility.MySQLConnUtils;

public class ConnectionUtils {

	public static Connection getConnection()	
            throws Exception {
		System.out.println("getConnection in ConnectionUtils");
       return MySQLConnUtils.getConnection();
  }
	public static void closeQuietly(Connection conn) {
		System.out.println("closeQuietly in ConnectionUtils");
	       try {
	           conn.close();
	       } catch (Exception e) {
	       }
	   }
	   public static void rollbackQuietly(Connection conn) {
	       try {
	    	   System.out.println("rollbackQuietly in ConnectionUtils");
	           conn.rollback();
	       } catch (Exception e) {
	       }
	   }

	

}
