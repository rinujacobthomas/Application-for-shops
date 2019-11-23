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
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {			//class to show product details
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Connection conn = MyUtils.getStoredConnection(request);		//getting stored connection object
		 ArrayList<ProductDetails> productlist = null;			//arraylist to store data
		   System.out.println("array created");
			try {
				productlist = DBUtils.displayProduct(conn);			//method to get data from database
			} catch (SQLException e) {
				e.printStackTrace();
			}
	   request.setAttribute("product", productlist);				//setting attribute
	   System.out.println("attribute set");
      RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productView.jsp");		//display page
      dir.forward(request, response);
	}

}
