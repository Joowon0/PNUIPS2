<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="pnuips.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("FUNCTION G<br>");
	out.println("Finding items which are commonly in top 10 best-selling"
			 + "of 20’s and 30’s in terms of number of sales.<br></br>");
		
	out.println(Functions.function_G());
%>
</body>
</html>