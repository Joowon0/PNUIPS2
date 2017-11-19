<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*" import="java.sql.*"
    import="java.utile.*" import="java.text.SimpleDateFormat" import="java.text.DateFormat"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println("FUNCTION D<br>");
	out.println("Finding top 3 best-selling items in a time interval (t1, t2) in terms of number of sales<br></br>");
	
	try{
		System.out.println("\nIn Try");
		
		System.out.println(request.getParameter("start_date"));
		System.out.println(request.getParameter("end_date"));

        out.println(Functions.function_D(request.getParameter("start_date"), request.getParameter("end_date")));
		        
		System.out.println("End Try\n");
	}catch(Exception e){//this generic but you can control another types of exception
	 //
	}
	
	
%>
</body>
</html>