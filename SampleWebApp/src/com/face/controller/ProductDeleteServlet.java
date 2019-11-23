package com.face.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.bo.ProductDetails;
import com.face.dao.DBUtils;
import com.face.dao.MyUtils;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {								// class to show and delete data from database 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection conn = MyUtils.getStoredConnection(request);					// to show data from database
		   ArrayList<ProductDetails> productlist = null;							// arraylist to store value from DBUtils
		   System.out.println("array created");
			try {
				productlist = DBUtils.displayProduct(conn);						// getting data from DBUtils
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   request.setAttribute("product", productlist);							// setting arraylist as an attribute in request
	   System.out.println("attribute set");
		RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productDelete.jsp");	//to next page
	      dir.forward(request, response);								// forwarding object contain data
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productD=request.getParameter("product");					// string to store product name
		Connection conn = MyUtils.getStoredConnection(request);				// getting stored connection
		try {
			DBUtils.deleteProduct(conn,productD);							//callimng method to delete product
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   ArrayList<ProductDetails> productlist = null;					//array list to display from database
		   System.out.println("array created");
			try {
				productlist = DBUtils.displayProduct(conn);					// storing data from database
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   request.setAttribute("product", productlist);						//setting array as an attribute
	   System.out.println("attribute set");
		 RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productDelete.jsp");		//displaying the same page
	      dir.forward(request, response);
	}

}
