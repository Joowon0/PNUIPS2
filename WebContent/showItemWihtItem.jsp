<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="pnuips.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String buyerid = request.getParameter("buyerID");
	int itemid = Integer.parseInt(request.getParameter("itemID"));

	out.println("SHOWING ITEM LIST with" + itemid +  "<br><br>");
	
	


	out.println(ItemDAO.selectAllItem(buyerid, itemid));
%>
</body>
</html>