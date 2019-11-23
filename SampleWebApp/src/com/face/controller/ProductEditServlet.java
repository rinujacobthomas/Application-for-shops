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
@WebServlet("/productEdit")
public class ProductEditServlet extends HttpServlet {							//class to edit product  details
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection conn = MyUtils.getStoredConnection(request);
		   ArrayList<ProductDetails> productlist = null;					// array to store data from database
		   System.out.println("array created");
			try {
				productlist = DBUtils.displayProduct(conn);					// method to display products from database
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   request.setAttribute("product", productlist);							// setting attribute
	   System.out.println("attribute set");
		RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productEdit.jsp");		//display edit page
	      dir.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productD=request.getParameter("product");									// variables to store new data
		String productQ=request.getParameter("productQuanntity");
		String productP=request.getParameter("productPrice");
		Connection conn = MyUtils.getStoredConnection(request);
		try {
			DBUtils.editProduct(conn,productD,productQ,productP);					// calling method to edit
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   ArrayList<ProductDetails> productlist = null;						// arraylist to store fdata to display
		   System.out.println("array created");
			try {
				productlist = DBUtils.displayProduct(conn);									//method to display products details from database
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   request.setAttribute("product", productlist);										// setting attribute
	   System.out.println("attribute set");
		 RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productEdit.jsp");		//  display product edit apge
	      dir.forward(request, response);
	}

}
