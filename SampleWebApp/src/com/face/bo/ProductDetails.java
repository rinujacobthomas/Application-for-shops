package com.face.bo;

public class ProductDetails {
private int productId;										//Private variables for storing values of product
private String productName;
private String productQuantity;
private String productPrice;
public int getProductId() {									// getters and setters for accessing these variales within different class
	return productId;
}
public void setProductId(int productId) {						
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getProductQuantity() {
	return productQuantity;
}
public void setProductQuantity(String productQuantity) {
	this.productQuantity = productQuantity;
}
public String getProductPrice() {
	return productPrice;
}
public void setProductPrice(String productPrice) {
	this.productPrice = productPrice;
}

}
