<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="request"
	class="kr.ac.hit.member.MemberInfo" />
<%
	member.setId("hihi");
member.setName("하이");
%>
<jsp:forward page="/useObject.jsp" />