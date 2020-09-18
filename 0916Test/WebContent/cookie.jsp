<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
response.addCookie(cookie);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=cookie.getName() %> 쿠키의 값 = "<%=cookie.getValue() %>"
</body>
</html>