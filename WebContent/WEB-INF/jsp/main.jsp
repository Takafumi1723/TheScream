<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Scream, java.util.List" %>
<%
	//セッションスコープに保存されているユーザ情報を取得
	User loginUser = (User) session.getAttribute("loginUser");
	// アプリケーションスコープに保存されているScreamリストを取得
	List<Scream> screamList = (List<Scream>) application.getAttribute("screamList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Scream</title>
</head>
<body>
<h1>The Scream Main</h1>
<p><%= loginUser.getName() %>, login.</p>
<a href="/TheScream/Logout">logout</a>

<p><a href="/TheScream/Main">refresh</a></p>

<form action="/TheScream/Main" method="post">
<input type="text" name="text">
<input type="submit" value="Scream!!">
</form>
<% for(Scream s : screamList) { %>
	<p><%= s.getUserName() %> : <%= s.getText() %></p>
<% } %>
</body>
</html>
