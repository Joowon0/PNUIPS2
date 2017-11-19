<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*"%>
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
	
	CartBean cart = CartDAO.selectCart(buyerID, itemID, sellerID);
	ItemBean item = ItemDAO.selectItem(itemID, sellerID);

	ClientBean client = ClientDAO.selectClient(buyerID);

	out.println("PURCHASE PAGE<br><br>");
	
	out.println("<br>Buyer ID      : " + buyerID
			  + "<br>Item ID       : " + itemID
			  + "<br>Seller        : " + sellerID
			  + "<br>Item Name     : " + item.getItemName()
			  + "<br>Price for one : ￦" + item.getPrice()
			  + "<br>Brand         : " + item.getBrand()
			  + "<br>Stock         : " + item.getStock()
			  + "<br>Sales         : " + item.getSales() + "<br><br>");

%>
	<form name = "" method="get" action="./MadePurchaseTemp.jsp">
		<input type="radio" name="coupon" value="0" checked> No Coupon<br>
		<% if(ClientDAO.totalPayment(buyerID) >= 200000 && client.getCoupon10()) { %>
		<input type="radio" name="coupon" value="10"> Coupon 10<br>
		<% } 
		   if(ClientDAO.totalPayment(buyerID) >= 500000 && client.getCoupon30()) { %>
		<input type="radio" name="coupon" value="30"> Coupon 30<br>
		<%} %>
		Number : 
		<input type="number" name="Amount_select" value= <%=cart.getAmount() %> min= 1 max= <%=item.getStock() %>>
		<br>
		<input type="hidden" name="buyerID" value="<%=buyerID%>">
		<input type="hidden" name="itemID" value="<%=itemID %>">
		<input type="hidden" name="sellerID" value="<%=sellerID %>">

		<input type="submit" value="BUY"/>
	</form>
</body>
</html>