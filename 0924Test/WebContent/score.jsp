<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" name="score">
	<c:choose>
		<c:when test="${param.score>=90 }">
당신은 A입니다.
</c:when>
		<c:when test="${param.score>=80 }">
당신은 B입니다.
</c:when>
		<c:when test="${param.score>=70 }">
당신은 C입니다.
</c:when>
		<c:when test="${param.score>=60 }">
당신은 D입니다.
</c:when>
		<c:otherwise>
F입니다~~~~~~
</c:otherwise>
	</c:choose>
</body>
</html>