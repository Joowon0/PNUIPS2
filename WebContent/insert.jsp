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
	StudentBean s = new StudentBean();
	s.setId(Integer.parseInt(request.getParameter("id")));
	s.setName(request.getParameter("name"));
	s.setScore(Float.parseFloat(request.getParameter("score")));

	int status = StudentDAO.insertStudent(s);
	if(status > 0)
	    out.println("success");
	else
	    out.println("fail : " + status);
%>
</body>
</html>