<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>
<form action="<%= request.getContextPath()%>/login.jsp ">
아이디 <input type = "text" name="memberId">
비밀번호 <input type = "password" name="password">
<input type="submit" value="로그인">
</form>
</body>
</html>