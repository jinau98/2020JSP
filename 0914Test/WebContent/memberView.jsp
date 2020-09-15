<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER VIEW</title>
</head>
<body>
<b>회원정보 확인</b><br>
아이디 : <%=request.getParameter("memId") %><br>
이름 : <%= request.getParameter("memName")%><br>
비밀번호 : <%= request.getParameter("memPw")%><br>
전화번호 : <%= request.getParameter("memPhone")%><br>
이메일 : <%= request.getParameter("memEmail")%><br>
주소 : <%= request.getParameter("memAddr")%><br>
</body>
</html>