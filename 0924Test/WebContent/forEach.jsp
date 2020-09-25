<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="kr.ac.hit.member.model.Member" %>

<%
	HashMap<String, Object> mapData = new HashMap<String, Object>();
	mapData.put("name", "최꾸마");
	mapData.put("today", new java.util.Date());
%>   
<c:set var="intArray" value="<%= new int[] {1,2,3,4,5} %>" />
<c:set var="map" value="<%=mapData %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="sum1" value="0"/>
<c:set var="sum2" value="0"/>
<c:set var="sum3" value="0"/>

<c:forEach var="i" begin="1" end="100" step="1">
<p hidden="true">${sum1=sum1+i}</p>
</c:forEach>

<c:forEach var="i" begin="1" end="100" step="2">
<p hidden="true">${sum2=sum2+i}</p>
</c:forEach>

<c:forEach var="i" begin="2" end="100" step="2">
<p hidden="true">${sum3=sum3+i}</p>
</c:forEach>

forEach 태그를 이용해서 1~100까지의 합 구하기 : ${sum1 }<Br>
forEach 태그를 이용해서 1~100까지 홀수의 합 구하기 :${sum2 }<br>
forEach 태그를 이용해서 1~100까지 짝수의 합 구하기 :${sum3 }

<h4>int형 배열</h4>

<c:forEach var="i" items="${intArray }" begin="2" end="4" varStatus="status">
${status.index }-${status.count }-[${i }]<br/>
</c:forEach>

Map

<c:forEach var="i" items="${map }">
${i.key } = ${i.value }<br/>
</c:forEach>
${map.today}

<%
	ArrayList<Member> memberList = new ArrayList<Member>();
	memberList.add(new Member("bobpul", "밥풀"));
	memberList.add(new Member("bookkue", "부끄"));
	memberList.add(new Member("kkuma", "꾸마"));
	
	request.setAttribute("memberList", memberList);
%>

<table border=1>
<tr>
<td>NO</td>
<td>ID</td>
<td>이름</td>
</tr>

<c:forEach var="member" items = "${memberList }" varStatus="status">
<tr>
<td>${status.count }</td>
<td>${member.mem_id}</td>
<td>${member.mem_name }</td>
</tr>
</c:forEach>
</table>

</body>
</html>