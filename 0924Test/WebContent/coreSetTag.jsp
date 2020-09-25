<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="kr.ac.hit.member.model.Member" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% request.setAttribute("user_name", "이름"); %>

<c:set var="name" value="홍길동"/>
<c:set var="age">20</c:set>

${name }<br/>
${age }</br>


<c:set var="user_id" value="Seventeen" scope="request"/>
유저명(리퀘스트객체) : ${user_name }<br />
유저아이디(리퀘스트객체) : ${user_id }<br />

<%
   Member member = new Member();
   member.setMem_name("밥풀");
   member.setMem_id("bobpul");
   request.setAttribute("member", member); //jstl사용하지 않았을 때
%>
<c:set var="member" value="<%= member %>" scope="request"/>
회원명 : ${member.mem_name } <br /> <!-- member.getmem_id가 아닌 변수명으로 바로 접근 가능 -->
회원아이디 : ${member.mem_id } <br />

<c:set target="${member }" property="mem_pwd" value="1234" />
회원패스워드 : ${member.mem_pwd }

<%
	Map<String, String> map = new HashMap<String, String>();
%>

<c:set target="<%= map %>" property = "host" value="localhost"/>

호스트 : <%= map.get("host") %><br/>

변수 제거 <br/>
삭제 전 : ${user_name } <br/>
<c:remove var = "user_name" scope="request"/>
삭제 후 : ${user_name }<br/>

</body>
</html>