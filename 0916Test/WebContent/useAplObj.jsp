<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	application.log("application 로그 메시지 기록");
%>
application 로그 메시지를 기록합니다.
<%
	log("로그 메시지 기록2");
%>
</body>
</html>