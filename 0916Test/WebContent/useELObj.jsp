<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("name", "정진유");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Object</title>
</head>
<body>
요청 URI : ${pageContext.request.requestURI }<br>
request의 name 속성 : ${requestScope.name}<br>
request의 id 속성 : ${requestScope.id}<br/>
code 파라미터 : ${param.code}<Br/>
memberId 파라미터 : ${param.memId}<br>
${"10"+1}<br>					<!-- 문자열 타입으로 숫자가 들어와도 숫자로 판단해서 계산함 -->
${null+1}<br>
${3/2}<br>						<!-- / 나누기는 div로 대체해서 사용 가능(굳...ㅇ...ㅣ.....?) -->
${3 div 2}<br/>
${"문자" +="열"}<br>
${"JAVA"}${"Programming"}<br>
${empty param.code}<br>
\${expr}
<!-- 비교연산자  
== 또는 eq
!= 또는 ne
 -->
</body>
</html>