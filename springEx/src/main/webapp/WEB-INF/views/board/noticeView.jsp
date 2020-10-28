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
		location.href="noticeForm?boSeqNo="+${board.bo_seq_no};
	});
	$('#btnDelete').click(function(){
		location.href="noticeDelete?boSeqNo="+${board.bo_seq_no};
	});
});
</script>
</head>
<body>
<div class="container">
	<h2 align="center">게시글 상세보기</h2>
	
	
	<table class ="table">
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
	</table>
	
	<p align="center">
		<c:if test="${not empty sessionScope.LOGIN_USER && sessionScope.LOGIN_USER.mem_id == board.bo_writer }">
			<input type="button" value="수정" class="btn btn-primary" id="btnEdit"/>
			<input type="button" value="삭제" class="btn btn-primary" id="btnDelete"/>
		</c:if>
		<input type="button" value="목록" class="btn btn-primary" onclick="location.href='noticeList?bo_type=NOTICE'"/>
	</p>
</div>
</body>
</html>