<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${empty sessionScope.LOGIN_USER}">
	<script>
		alert("로그아웃");
		location.href = "loginForm.do";
	</script>
</c:if>
<body>

</body>
</html>