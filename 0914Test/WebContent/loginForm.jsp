<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
<b>로그인</b>
<form action="/0914Test/loginCheck.jsp" method="post">
		아이디 : <input type="text" name="memId"><br> 
		패스워드 : <input type="password" name="memPw"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>