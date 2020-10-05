<%@page import="java.sql.*"%>
<%@page import="kr.ac.hit.common.jdbc.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try(Connection conn = ConnectionProvider.getConnection()){
	out.println("커넥션 연결 성공");
}catch(SQLException e){
	out.println("커넥션 연결 실패");
	application.log("커넥션 연결 실패", e);
}
%>
</body>
</html>