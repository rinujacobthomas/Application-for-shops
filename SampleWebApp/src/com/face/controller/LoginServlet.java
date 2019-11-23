package com.face.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.face.bo.UserAccount;
import com.face.dao.DBUtils;
import com.face.dao.MyUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login" })


public class LoginServlet extends HttpServlet {			//Class to Show Login Page and to validate username and password
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome to Login Servlet");
	       RequestDispatcher dispatcher //
	               = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");		//to Show login page
	       dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");						// variables to store username and password
	       String password = request.getParameter("password");
	       String rememberMeStr = request.getParameter("rememberMe");
	       boolean remember = "Y".equals(rememberMeStr);
	       UserAccount user = null;			// object to get values
	       boolean hasError = false;
	       String errorString = null;
	       if (userName == null || password == null || userName.length() == 0 || password.length() == 0) { 	//username and password validation
	           hasError = true;
	           errorString = "Required username and password!";
	       } else {
	           Connection conn = MyUtils.getStoredConnection(request);			//getting the object of connection 
	           try {
	               // Find the user in the DB.
	               user = DBUtils.findUser(conn, userName, password);			// validating username and password and getting user data
	               if (user == null) {
	                   hasError = true;
	                   errorString = "User Name or password invalid";
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	               hasError = true;
	               errorString = e.getMessage();
	           }
	       }
	       // If error, forward to /WEB-INF/views/login.jsp
	       if (hasError) {										// if password or username is incorrect
	           user = new UserAccount();
	           user.setUserName(userName);
	           user.setPassword(password);
	           // Store information in request attribute, before forward.
	           request.setAttribute("errorString", errorString);
	           request.setAttribute("user", user);
	           // Forward to /WEB-INF/views/login.jsp
	           RequestDispatcher dispatcher //
	                   = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp"); 		// showing the same login page
	           dispatcher.forward(request, response);
	       }
	       // If no error
	       // Store user information in Session
	       // And redirect to userInfo page.
	       else {																		//if user is genuine
	           HttpSession session = request.getSession(); 
	           MyUtils.storeLoginedUser(session, user);									//storing login status
	           // If user checked "Remember me".
	           if (remember) {
	               MyUtils.storeUserCookie(response, user);								// storing to cookie if remember is checked
	           }
	           // Else delete cookie.
	           else {
	             //  MyUtils.deleteUserCookie(response);
	           }
	           // Redirect to userInfo page.
	           response.sendRedirect(request.getContextPath() + "/userInfo");     // showing  user info page
	       }
	}

}
