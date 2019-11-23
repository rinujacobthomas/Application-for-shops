<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
    <%@ page import="com.face.bo.ProductDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="menuLogin.jsp"></jsp:include>
   <h3>Product List</h3>
    <div style="margin-left: 10em;" >
    <table>
        <tr><th>Product Id</th><th>Product Name</th><th>Product Quantity</th><th>Product Price</th></tr>
    <%ArrayList<ProductDetails> productlist=(ArrayList)request.getAttribute("product");
    for(int i=0;i<productlist.size();i++)
    {
    int id=productlist.get(i).getProductId();
    String name=productlist.get(i).getProductName();
    String quantity=productlist.get(i).getProductQuantity();
    String price=productlist.get(i).getProductPrice();
    %>
    <tr>
        <td><%=id %></td><td><%=name %></td><td><%=quantity %></td><td><%=price %></td>
    </tr>
    
    <%} %>
    </table>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
    </body>
</html>