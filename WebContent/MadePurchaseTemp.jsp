<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*" import="java.sql.*" import="java.text.SimpleDateFormat"%>
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
	String Coupon = request.getParameter("coupon");
	Boolean coupon10 = false, coupon30 = false;
	
	if (Coupon.equals("10") )
		coupon10 = true;
	if (Coupon.equals("30") )
		coupon30 = true;
	
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     out.println(timestamp + "<br>");
     out.println(sdf.format(timestamp) + "<br>");
     

	
	out.println("<br>"
			+ buyerID + "<br>"
			+ itemID+ "<br>"
			+ sellerID+ "<br>"
			+ Amount+ "<br>"
			+ Coupon+ "<br>");
	PurchaseBean purchase = new PurchaseBean();
	purchase.initiate(buyerID, itemID, sellerID, Amount, 0, timestamp, coupon10, coupon30);
	PurchaseDAO.insertPurchase(purchase);
	
%>
<script>window.location.href="./showPurchase4Client.jsp?var=<%= buyerID%>";
</script>
</body>
</html>