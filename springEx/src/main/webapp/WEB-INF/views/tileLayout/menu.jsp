<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath }/">Home</a></li>
			<c:if test="${sessionScope.LOGIN_USER.mem_type == 'A' }">
			<li><a href="${pageContext.request.contextPath }/member/memberList">회원관리</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/board/boardList?bo_type=BBS">게시판</a></li>
			<li><a href="${pageContext.request.contextPath }/board/noticeList?bo_type=NOTICE">공지사항</a></li>
			<li><a href="#">자료실</a></li>
			<li>
				<a href="#" class="dropdown-toggle" data-toggle="dropdown"
				role="button" aria-haspopup="true" aria-expanded="false">			<!-- 드롭다운 메뉴 예제 -->
					Hello
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">Test1</a></li>
					<li><a href="#">Test2</a></li>
				</ul>
			</li>
		</ul>
		<c:if test="${sessionScope.LOGIN_USER == null }">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath }/login/loginForm">Login</a></li>
				<li><a href="${pageContext.request.contextPath}/member/memberForm">회원가입</a></li> 
				
			</ul>
		</c:if>
				<c:if test="${sessionScope.LOGIN_USER != null }">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath }/login/logout">Logout</a></li>
				
			</ul>
		</c:if>
	</div>
</body>
</html>