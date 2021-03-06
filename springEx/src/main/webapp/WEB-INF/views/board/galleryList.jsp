<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function doSearch(page){
		var frm = document.searchForm;
		
		if (frm.searchType.value != "" && frm.searchWord.value == "") {
			alert("검색어를 입력하세요.");
			return false;
		}
		
		frm.currentPage.value = page;
		frm.action = "galleryList";
		frm.submit();
	}
</script>
<title>galleryList</title>
</head>
<body>
<div class="container">
	<h2 align="center">게시글 목록</h2>
	<p align="right">
		<input type="button" value="글쓰기" class="btn btn-primary" onclick="location.href='galleryForm'"/>
	</p>
		<form name="searchForm" method="post">
	<p align="center">
			<input type="hidden" name="currentPage" value="${param.currentPage }" />		<!-- 커렌트페이지, 게시판 타입 값 히든으로 넘겨줌 -->
			<input type="hidden" name="bo_type" value="GALLERY" />
			<select name="searchType">
				<option value="">전체</option>
				<option value="01" ${param.searchType =='01' ? 'selected' : '' }>제목</option>
				<option value="02" ${param.searchType =='02' ? 'selected' : '' }>내용</option>
				<option value="03" ${param.searchType =='03' ? 'selected' : '' }>제목+내용</option>
				<option value="04" ${param.searchType =='04' ? 'selected' : '' }>작성자</option>
			</select>
			<input type="text" name="searchWord" size="40" value="${param.searchWord }">
			<input type="button" value="검색 " onclick="doSearch(1);"/>
		
		<label>총 게시글 : ${pagingUtil.totalCount }</label>
		<span style="float : right;">
			<select name="pageSize" onchange="doSearch(1);">
				<option value="10" ${param.pageSize=='10' ? 'selected' : '' }>10개</option>
				<option value="20" ${param.pageSize=='20' ? 'selected' : '' }>20개</option>
				<option value="50" ${param.pageSize=='50' ? 'selected' : '' }>50개</option>
			</select>
		</span>
	</p>
		</form>
	
	<table class="table table-bordered table-hover">
		<tbody>
			<c:if test="${not empty galleryList }">
				<c:forEach var="board" items="${galleryList }">
				<tr>
					<td rowspan="5" style="text-align:center;">
						<a href="galleryView?boSeqNo=${board.bo_seq_no}">
							<img class="img-thumbnail" style="width : 150px; height : 150px;" src="${pageContext.request.contextPath }/common/display?file_seq_no=${board.file_seq_no}"/>
						</a>
					</td>
				</tr>
				<tr>
					<td><a href="galleryView?boSeqNo=${board.bo_seq_no}">${board.bo_title }</a></td>
					</tr>
				<tr>
					<td>${board.bo_writer_name }</td>
					</tr>
				<tr>
					<td>${board.reg_date }</td>
					</tr>
				<tr>
					<td>${board.bo_hit_cnt }</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty galleryList }">
				<tr>
					<td colspan="5">게시글이 존재하지 않습니다.</td> 
				</tr>
			</c:if>
		</tbody>
	</table>
	<div style="text-align:center;">
		<ul class="pagination">
			${pagingUtil.pageHtml}
		</ul>
	</div>
	<!-- 페이지 네비게이션 END -->
</div>
	
</body>
</html>