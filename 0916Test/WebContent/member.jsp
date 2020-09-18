<%@page import="sujin.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = new Member("hihi", 1234);
	request.setAttribute("member", member);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
\${member.getName()} : ${member.getAge()}
</body>
</html>