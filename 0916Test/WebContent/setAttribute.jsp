<%@page import="java.util.Date"%>
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
	String user_name="홍길동";
	int user_age = 25;
	Date date = new Date();
	
	request.setAttribute("req_name", user_name);
	request.setAttribute("req_age", user_age);
	request.setAttribute("req_date", date);
	
	session.setAttribute("ses_name", user_name);
	session.setAttribute("ses_age", user_age);
	session.setAttribute("ses_date", date);
	
	application.setAttribute("app_name", user_name);
	application.setAttribute("app_age", user_age);
	application.setAttribute("app_date", date);
%>

<h3>request의 속성정보</h3>

이름 : <%= request.getAttribute("req_name") %><br>
나이 : <%= request.getAttribute("req_age") %><br>
날짜 : <%= request.getAttribute("req_date") %><br>

<h3>session의 속성정보</h3>

이름 : <%= session.getAttribute("ses_name") %><br>
나이 : <%= session.getAttribute("ses_age") %><br>
날짜 : <%= session.getAttribute("ses_date") %><br>

<h3>application의 속성정보</h3>

이름 : <%= application.getAttribute("app_name") %><br>
나이 : <%= application.getAttribute("app_age") %><br>
날짜 : <%= application.getAttribute("app_date") %><br>


</body>
</html>