<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#btnEdit').click(function(){
		location.href="galleryForm?boSeqNo="+${board.bo_seq_no};
	});
	$('#btnDelete').click(function(){
		location.href="galleryDelete?boSeqNo="+${board.bo_seq_no};
	});
});
</script>
</head>
<body>
<div class="container">
	<h2 align="center">게시글 상세보기</h2>
	
	
	<table class ="table">
		  			<!-- 글을 볼 수 있는 조건  (1)bo_open_yn이 y이거나(전체공개) (2)세션에 저장된 사용자 아이디와 작성자 아이디가 같음 -->
	<c:choose>		
			<c:when test="${board.bo_open_yn =='Y' || (not empty sessionScope.LOGIN_USER && sessionScope.LOGIN_USER.mem_id == board.bo_writer) }">
				<tr>
					<td>제목</td>
					<td>${board.bo_title }</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${board.bo_writer_name }</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${board.reg_date }</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${board.bo_hit_cnt }</td>
				</tr>
				<tr>
					<td>공개여부</td>
					<td>${board.bo_open_yn == 'Y' ? '공개' : '비공개' }</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td>
						<c:if test="${board.fileList != null }">
							<c:forEach var="fileItem" items="${board.fileList}">
								<div>
									<a href="${pageContext.request.contextPath}/common/download?file_seq_no=${fileItem.file_seq_no}">${fileItem.file_name }</a> ${fileItem.file_fancy_size }
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${board.fileList == null }">
							파일이 없습니다.
						</c:if>
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<!-- 줄바꿈 적용 -->
					<td style="white-space:pre;">${board.bo_content }</td>
				</tr>				
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">비밀글입니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<p align="center">
		<c:if test="${not empty sessionScope.LOGIN_USER && sessionScope.LOGIN_USER.mem_id == board.bo_writer }">
			<input type="button" value="수정" class="btn btn-primary" id="btnEdit"/>
			<input type="button" value="삭제" class="btn btn-primary" id="btnDelete"/>
		</c:if>
		<input type="button" value="목록" class="btn btn-primary" onclick="location.href='galleryList?bo_type=GALLERY'"/>
	</p>
</div>
</body>
</html>