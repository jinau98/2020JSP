<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보 조회</title>
</head>
<body>
MEMBERID = <%= session.getAttribute("MEMBERID") %><Br/>
NAME : <%= session.getAttribute("NAME")%>
</body>
</html>