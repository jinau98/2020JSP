<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="request" class="kr.ac.hit.member.MemberInfo"/>
<html>
<head> <title>인사말</title></head>
<body>
<%=member.getName() %>(<%=member.getId() %>) 회원님 하이!
</body>
</html>