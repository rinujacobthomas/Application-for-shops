<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
   <jsp:include page="menuLogin.jsp"></jsp:include>
   <form action="${pageContext.request.contextPath}/productView" method="POST">
<table border="0">
<tr>
    <td>Product ID</td>
    <td><input type="text" name="productId" value="${product.productId}"></td>
</tr>
<tr>
        <td>Name</td>
        <td><input type="text" name="productName" value="${product.productName}"></td>
    </tr>
    <tr>
            <td>Quantity</td>
            <td><input type="text" name="quantity" value="${product.quantity}"></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" name="price" value="${product.price}"></td>
        </tr>
        <tr>
            <td><button type="submit">Add</button></td>
        </tr>
</table>

</form>
    <br />
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>