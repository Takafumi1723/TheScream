<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Scream</title>
</head>
<body>
<h1>The Scream Login</h1>
<% if(loginUser != null){ %>
	<p>Successful Login.</p>
	<p>Welcome to The Scream. <%= loginUser.getName() %>
	<a href="/TheScream/Main">Let's Scream!</a>
<% } else { %>
	<p>Failed Login.</p>
	<a href="/TheScream/index.jsp">to Top</a>
<% } %>
</body>
</html>