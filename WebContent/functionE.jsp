<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("FUNCTION E<br>");
	out.println("For a given seller, finding items that he/she does not sell but are among top 10 best-selling of highest-inco<br></br>");
	
	int idTemp = Integer.parseInt(request.getParameter("sellerID"));
	
	out.println(Functions.function_E(idTemp));
	
%>
</body>
</html>