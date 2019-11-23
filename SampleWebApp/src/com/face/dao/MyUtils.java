package com.face.dao;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.face.bo.UserAccount;

public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	   private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
																		   // Store Connection in request attribute.
																		   // (Information stored only exist during requests)
	   public static void storeConnection(ServletRequest request, Connection conn) {
		   System.out.println("StoreConnection in myUtils");
	       request.setAttribute(ATT_NAME_CONNECTION, conn);
	   }
	   															// Store user info in Session.
	   public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
		   System.out.println("StoreLoginedUser in myUtils");
		   															// On the JSP can access via ${loginedUser}
	       session.setAttribute("loginedUser", loginedUser);
	   }															// Store info in Cookie
	   public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
	       System.out.println("Store user cookie");
	       Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
	       																			// 1 day (Converted to seconds)
	       cookieUserName.setMaxAge(24 * 60 * 60);
	       System.out.println("Store user cookies in myUtils");
	       response.addCookie(cookieUserName);
	   }
	   																				// Get the user information stored in the session.
	   public static UserAccount getLoginedUser(HttpSession session) {
		   System.out.println("getloginuser in myUtils");
	       UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
	       return loginedUser;
	   }
	   public static Connection getStoredConnection(ServletRequest request) {
		   System.out.println("getstoredConnection in myUtils");
	       Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
	       return conn;
	   }

	   
}
