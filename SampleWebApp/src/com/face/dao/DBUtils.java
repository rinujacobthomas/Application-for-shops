package com.face.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.face.bo.ProductDetails;
import com.face.bo.UserAccount;

public class DBUtils {
	public static UserAccount findUser(Connection conn, 							// to validate user
	           String userName, String password) throws SQLException {
	       String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "
	               + " where a.User_Name = ? and a.password= ?";
	       PreparedStatement pstm = conn.prepareStatement(sql);
	       pstm.setString(1, userName);
	       pstm.setString(2, password);
	       ResultSet rs = pstm.executeQuery();
	       if (rs.next()) {
	           String gender = rs.getString("Gender");
	           UserAccount user = new UserAccount();
	           user.setUserName(userName);
	           user.setPassword(password);
	           user.setGender(gender);
	           System.out.println("Dbutils");
	           return user;
	       }
	       return null;
	   }

	public static void addProduct(Connection conn, int productId, String productName,
			String productQuantity, String productPrice) throws SQLException 
	{
		Statement st = conn.createStatement();
		boolean a=st.execute("insert into product values('"+productId+"','"+productName+"','"+productQuantity+"','"+productPrice+"')");
		ProductDetails product= new ProductDetails();
		System.out.println(a);
	}

	public static ArrayList displayProduct(Connection conn) throws SQLException {				//display method
		ArrayList<ProductDetails> productlist = new ArrayList<ProductDetails>();
		System.out.println("displayProduct in DButils");
		 Statement st = conn.createStatement();
         ResultSet srs = st.executeQuery("SELECT * FROM product");
         System.out.println("value stored in resultset");
         while (srs.next()) {
             ProductDetails pro = new ProductDetails();
             pro.setProductId(Integer.parseInt(srs.getString("product_id")));	//setting data from database to ProductBO object
             pro.setProductName(srs.getString("product_name"));
             pro.setProductQuantity(srs.getString("product_quantity"));
             pro.setProductPrice(srs.getString("product_price"));
             productlist.add(pro);												//adding object to arraylist
         }
         System.out.println("returning object arraylist");
         return productlist;											//returning arraylist object
		
	}

	public static void deleteProduct(Connection conn, String productD) throws SQLException {		//delete method
		Statement st = conn.createStatement();
		boolean a=st.execute("delete from product where product_name='"+productD+"'");
	}

	public static void editProduct(Connection conn, String productD, String productQ, String productP) throws SQLException {
		Statement st = conn.createStatement();
		boolean a=st.execute("update product set product_price='"+productP+"',product_quantity='"+productQ+"' where product_name='"+productD+"'");
		
	}

}
