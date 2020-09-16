<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외 발생
<p>
에러 타입 : <%= exception.getClass().getName() %>
에러 메세지 : <%= exception.getMessage() %>
</body>
</html>