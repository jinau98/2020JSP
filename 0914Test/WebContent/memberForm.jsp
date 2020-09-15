<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBERFORM</title>
</head>
<body><b>회원가입</b><br>
<form action="/0914Test/memberView.jsp" name="memberForm">
아이디 : <input type="text" name="memId"><br>
이름 : <input type="text" name="memName"><br>
비밀번호 : <input type="password" name="memPw"><br>
비밀번호 확인 : <input type="password" name="memPwCh"><br>
전화번호 : <input type="text" name="memPhone"><br>
이메일 : <input type="text" name="memEmail"><br>
주소 : <input type="text" name="memAddr"><br>
<input type="submit" value="등록">
</form>
</body>
</html>