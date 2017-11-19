<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("Administer page");

	ClientBean c = new ClientBean();
	c.setID(request.getParameter("id"));
	c.setPassword(request.getParameter("password"));
	
	ClientBean d = ClientDAO.selectClient(c.getId());
	if((c.getPassword()).equals(d.getPassword()) ) {
		out.println("<br>Right password");
		out.println("<br><br>Client ID : " + c.getId());
		out.println( ClientDAO.showClass(d.getId() ));
		
		%>
		<br></br>
		<form name="f_SHOP" method="get" action="./showItem.jsp">
			<input type="hidden" name="var" value="<%=c.getId() %>">
			<input type="submit" value="SHOP" onclick=passID1/>
		</form>
		<form name="f_CART" method="get" action="./showCart4Client.jsp">
			<input type="hidden" name="var" value="<%=c.getId() %>">
			<input type="submit" value="CART"/>
		</form>
		
		<form name="f_CART" method="get" action="./showPurchase4Client.jsp">
			<input type="hidden" name="var" value="<%=c.getId() %>">
			<input type="submit" value="View My Purchase"/>
		</form>
		<%
		/*
		<form name="f1" method="get" action="./select.jsp">
			<input type="submit" value="view all cart"/>
		</form>
		<form name="f2" method="get" action="./select.jsp">
			<input type="submit" value="view all client"/>
		</form>
		<form name="f3" method="get" action="./select.jsp">
			<input type="submit" value="view all purchase"/>
		</form>
		<form name="f4" method="get" action="./select.jsp">
			<input type="submit" value="view all seller"/>
		</form>
		*/
		%>
		<br></br> Function A
		<form name="fa" method="get" action="./functionA.jsp">
		    userID : <input type="text" name="userID"/>
			<input type="submit" value="FUNCTION A"/>
			<br></br>checking each client’s purchase history<br></br>
		</form>
		
		<br></br> Function B
		<form name="fb" method="get" action="./functionB.jsp">
		    seller ID : <input type="text" name="sellerID"/>
			<input type="submit" value="FUNCTION B"/>
			<br></br>checking each seller’s sale history<br></br>
		</form>
		
		<br></br> Function C
		<form name="fc" method="get" action="./functionC.jsp">		
			<input type="submit" value="FUNCTION C"/>
			<br></br>checking each seller’s number of sales<br></br>
		</form>
		
		<br></br> Function D
		<form name="fd" method="get" action="./functionD.jsp">
			Start : <input type="datetime" name="start_date">
			<br>
			End   : <input type="datetime" name="end_date">
			<input type="submit" value="FUNCTION D"/>
			<br></br> Finding top 3 best-selling items in a time interval (t1, t2) in terms of number of sales<br></br>
		</form>
		
		<br></br> Function E
		<form name="fe" method="get" action="./functionE.jsp">
			seller ID : <input type="text" name="sellerID"/>
			<input type="submit" value="FUNCTION E"/>
			<br></br>For a given seller, finding items that he/she does not sell
            <br></br>but are among top 10 best-selling of highest-income<br></br>
		</form>
		
		<br></br> Function F
		<form name="ff" method="get" action="./functionF.jsp">
			<input type="submit" value="FUNCTION F"/>
			<br>Finding items whose stocks are less than the total amount in carts.<br>
		</form>
		
		<br></br> Function G
		<form name="fg" method="get" action="./functionG.jsp">
			<input type="submit" value="FUNCTION G"/>
			<br>Finding items which are commonly in top 10 best-selling of 20’s and 30’s in terms of number of sales.
		</form><br>
<%
	}
		
	else
		out.println("<br>Wrong password");
%>

</body>
</html>