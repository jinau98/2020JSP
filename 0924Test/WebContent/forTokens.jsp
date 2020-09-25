<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	콤마와 점을 구분자로 사용 :
	<br>
	<c:forTokens var="token" items="빨강, 주황. 노랑. 초록, 파랑, 남색. 보라" delims=",.">
${token }<br />
	</c:forTokens>
	<c:choose>
	<c:when test="${param.type == 'blog'}">
		<c:import url="http://serach.daum.net/search?">
			<c:param name="w=blog" value="blog" />
			<c:param name="q" value="보라매공원" />
		</c:import>
	</c:when>

	<c:when test="${param.type =='youtube' }">
		<c:import url="https://www.youtube.com/results">
			<c:param name="search_query" value="SEVENTEEN" />
		</c:import>
	</c:when>
	
	<c:otherwise>
		<c:import url="use_import_tag_help.jsp">
			<c:param name="message" value="선택해주세요" />
		</c:import>
	</c:otherwise>
	</c:choose>
</body>
</html>