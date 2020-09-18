<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String memberId=(String)session.getAttribute("MEMBERID");
    boolean login = memberId ==null? false : true;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(login){
		%>
		아이디 "<%=memberId %>"로 로그인
	<%
	} else{
		%>
		로그인 X
		<%
	}
%>
</body>
</html>