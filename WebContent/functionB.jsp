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
	out.println("FUNCTION B<br>");
	out.println("checking each seller’s sale history<br></br>");
	
	int idTemp = Integer.parseInt(request.getParameter("sellerID"));
	
	out.println(Functions.function_B(idTemp));
	
%>
</body>
</html>