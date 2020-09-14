<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재 시간</title>
</head>
<body>
<% Date nowTime = new Date(); %>
현재 시간은 <%=nowTime %> 입니다.
</body>
</html>