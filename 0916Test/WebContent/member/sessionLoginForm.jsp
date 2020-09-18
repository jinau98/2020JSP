<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="<%=request.getContextPath() %>/member/sessionLogin.jsp" method="post">
아이디 <input type="text" name="id" size="10">
PW <input type="password" name="pw" size="10">
<input type="submit" value="로그인">
</form>
</body>
</html>