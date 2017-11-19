<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String buyerID = request.getParameter("buyerID");
	int itemID = Integer.parseInt(request.getParameter("itemID"));
	int sellerID = Integer.parseInt(request.getParameter("sellerID"));
	int Amount = Integer.parseInt(request.getParameter("Amount_select"));
	
	CartBean cart = new CartBean();
	cart.initiate(buyerID, itemID, sellerID, Amount);
	CartDAO.insertCart(cart);
	
	cart.xprint();
%>

<script>window.location.href="./showCart4Client.jsp?var=<%= buyerID%>";
</script>
</body>
</html>