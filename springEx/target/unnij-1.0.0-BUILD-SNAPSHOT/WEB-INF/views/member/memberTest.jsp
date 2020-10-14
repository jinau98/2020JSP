<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<!-- member라는 객체? 변수? 에 데이터 저장 -->
			<tr>
				<td>${member.mem_id}</td>
				<td>${member.mem_name }</td>
				<td>${member.mem_email }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>