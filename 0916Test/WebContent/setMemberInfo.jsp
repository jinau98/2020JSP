<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
session.setAttribute("MEMBERID", "hi");
session.setAttribute("NAME", "하이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션에 정보 저장</title>
</head>
<body>
	세션에 정보를 저장하였습니다. <br>
	MEMBERID :
	<%= session.getAttribute("MEMBERID") %>
	NAME :
	<%= session.getAttribute("NAME") %>
</body>
</html>