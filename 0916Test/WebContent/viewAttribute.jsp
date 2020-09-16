<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>request의 속성정보</h3>

이름 : <%= request.getAttribute("req_name") %>
나이 : <%= request.getAttribute("req_age") %>
날짜 : <%= request.getAttribute("req_date") %>

<h3>session의 속성정보</h3>

이름 : <%= session.getAttribute("ses_name") %>
나이 : <%= session.getAttribute("ses_age") %>
날짜 : <%= session.getAttribute("ses_date") %>

<h3>application의 속성정보</h3>

이름 : <%= application.getAttribute("app_name") %>
나이 : <%= application.getAttribute("app_age") %>
날짜 : <%= application.getAttribute("app_date") %>
</body>
</html>