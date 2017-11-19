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

	String id = request.getParameter("var");

	out.println("SHOWING CART LIST<br><br>");
		
	out.println("Client ID : " + id);
	out.println( ClientDAO.showClass(id) +"<br>");
	
	
	out.println(CartDAO.showCart4Person(id));
%>

</body>
</html>