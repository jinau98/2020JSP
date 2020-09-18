<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memberInfo" class="kr.ac.hit.member.MemberInfo" />
<jsp:setProperty name="memberInfo" property="*" />
<jsp:setProperty name="memberInfo" property="password" value="<% memberInfo.getId(); %>"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
   <tr>
      <td>아이디</td>
      <td><jsp:getProperty property="id" name="memberInfo"/></td>
      <td>암호</td>
      <td><jsp:getProperty property="password" name="memberInfo"/></td>
   </tr>
   <tr>
      <td>이름</td>
      <td><jsp:getProperty property="name" name="memberInfo"/></td>
      <td>이메일</td>
      <td><jsp:getProperty property="email" name="memberInfo"/></td>
</table>
</body>
</html>