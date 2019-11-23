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
import javax.servlet.http.HttpSession;

import com.face.bo.ProductDetails;
import com.face.bo.UserAccount;
import com.face.dao.DBUtils;
import com.face.dao.MyUtils;
import com.face.utility.MySQLConnUtils;

/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/productView")
public class ProductViewServlet extends HttpServlet {		//class to add product
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			int productId = Integer.parseInt(request.getParameter("productId"));	//variables to store product details
	       String productName = request.getParameter("productName");
	       String productQuantity = request.getParameter("quantity");
	       String productPrice = request.getParameter("price");	    
	      ProductDetails product = new ProductDetails();
	      product = new ProductDetails();			//setting values to productBO class
          product.setProductId(productId);
          product.setProductName(productName);
          product.setProductQuantity(productQuantity);
          product.setProductPrice(productPrice);
	           Connection conn = MyUtils.getStoredConnection(request);		//getting stored connection
			   try {
				DBUtils.addProduct(conn, productId, productName,productQuantity,productPrice);		//calling method to add productt
			} catch (SQLException e) {
				e.printStackTrace();
			}
				   ArrayList<ProductDetails> productlist = null;				// arraylist to store data from database
					try {
						productlist = DBUtils.displayProduct(conn);				//method too get data from database
					} catch (SQLException e) {
						e.printStackTrace();
					}
			   request.setAttribute("product", productlist);					//setting attribute
			   System.out.println("attribute set");
	          RequestDispatcher dir = request.getRequestDispatcher("/WEB-INF/views/productView.jsp");		// page
	          dir.forward(request, response);
}

}
