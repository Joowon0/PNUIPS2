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
	out.println("SHOWING ITEM LIST<br><br>");
	String id = request.getParameter("var");
%>
	<form name="fa" method="get" action="./showItemWihtItem.jsp">
		<input type="hidden" name="buyerID" value="<%= id %>">
    	<input type="text" name="itemID"/>
		<input type="submit" value="SEARCH"/>
		<br></br>
	</form>
<%	
	out.println(ItemDAO.selectAllItem(id));
%>
</body>
</html>