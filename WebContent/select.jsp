<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pnuips.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	   request.setAttribute("student", StudentDAO.selectStudent( Integer.parseInt(request.getParameter("id")) ));
	%>
	<table>
	<tr>
	    <td>${student.id}</td>
	    <td>${student.name}</td>
	    <td>${student.score}</td>
    </tr>
	</table>
</body>
</html>