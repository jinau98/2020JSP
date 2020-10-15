<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script>
function goViewPage(seqNo, currentPage){		//목록 페이지로 돌아가기 구현 위해서 currentPage도 같이 보내줌
	location.href="memberView?seqNo="+seqNo+"&currentPage="+currentPage;
}
	function doSearch(page) {
		var frm = document.searchForm;

		if (frm.searchType.value != "" && frm.searchWord.value == "") {
			alert("검색어를 입력하세요.");
			return false;
		}

		frm.currentPage.value = page;
		frm.action = "memberList";
		frm.submit();
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form name="searchForm" method="post">
			<input type="hidden" name="currentPage" value="${param.currentPage }">
			<select name="searchType">
				<option value="">전체</option>
				<option ${param.searchType=='id' ? 'selected' : '' } value="id">아이디</option>
				<option ${param.searchType=='name' ? 'selected' : '' } value="name">이름</option>
			</select> <input type="text" size="20" name="searchWord"
				value="${param.searchWord }"> <input type="button"
				value="검색" onclick="doSearch(1)">
			<p>
				<label style="float: left;">총 회원 수 :
					${pagingUtil.totalCount}</label> <span style="float: right;"> <label>페이지사이즈
						: </label> <select name="pageSize" onchange="doSearch(1);">
						<option ${param.pageSize=='10' ? 'selected' : '' } value="10">10개</option>
						<option ${param.pageSize=='20' ? 'selected' : '' } value="20">20개</option>
						<option ${param.pageSize=='50' ? 'selected' : '' } value="50">50개</option>
				</select>
				</span>
			</p>
		</form>


		<!-- 검색 폼 -->
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>순번</th>
					<th>아이디</th>
					<th>이름</th>
					<th>핸드폰</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty memberList }">
					<c:forEach var="member" items="${memberList }">
						<c:url var="viewURL" value="memberView">
							<c:param name="seqNo" value="${member.mem_seq_no }" />
							<c:param name="currentPage" value="${pagingUtil.currentPage }" />
						</c:url>
						<tr
							onclick="goViewPage(${member.mem_seq_no}, ${pagingUtil.currentPage })">
							<td>${member.mem_seq_no }</td>
							<td>${member.mem_id }</td>
							<td><a href="${viewURL}">${member.mem_name }</a></td>
							<td>${member.mem_phone }</td>
							<td>${member.mem_email }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty memberList }">
					<tr>
						<td colspan="5" align="center">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<div style="text-align: center;">
			<ul class="pagination">${pagingUtil.pageHtml }
			</ul>
		</div>
	</div>
</body>
</html>