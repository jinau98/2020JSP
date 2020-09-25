<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>if태그</title>
</head>
<body>
<c:if test="true">
무조건 수행<br>
</c:if>

<c:if test="${param.name=='hong' }">
name파라미터 값이 ${param.name }입니다.<br>
</c:if>

<c:if test="${18< param.age }">
당신의 나이는 18세 이상입니다.<br>
</c:if>

<c:choose>
<c:when test="${param.name=='hong' }">
당신의 이름은 ${param.name }입니다.
</c:when>
<c:when test="${param.age>20 }">
당신은 20세 이상입니다.
</c:when>
<c:otherwise>
넌 뭐니?
</c:otherwise>
</c:choose>
</body>
</html>
