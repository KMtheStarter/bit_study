<%@page import="java.sql.Connection"%>
<%@page import="conn.ConnDriver"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>connectionTestDemo</title>
</head>
<body>
<%
	Connection con = ConnDriver.getMyConnection();
	System.out.println("Connection Status: " + con.toString());
%>
<p style="color: red; font-size: 30px;"><%= con.toString() %></p>
</body>
</html>
<%
	con.close();
%>