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
	out.println("FUNCTION A<br>");
	out.println("checking each client’s purchase history<br>");
	
	String idTemp = request.getParameter("userID");
	
    List<PurchaseBean> daos = Functions.function_A(idTemp);
    
    out.println("<br>");
    for(int i=0 ; i < daos.size() ; i++) { 
    	out.println(daos.get(i).yprint()+"\n");
    }
%>
</body>
</html>