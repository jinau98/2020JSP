<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER VIEW</title>
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<tr>
				<td>이름</td>
				<td>${member.mem_name}</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${member.mem_id }</td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td>${member.mem_phone }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${member.mem_email }</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<p>${member.mem_zipcode }</p>
					<p>${member.mem_addr_master }</p>
					<p>${member.mem_addr_detail }</p>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="button" value="수정" class="btn btn-default" onclick="location.href='memberForm?seqNo=${member.mem_seq_no}'"> 
				<input type="button" value="삭제" class="btn btn-default" onclick="location.href='memberDelete?seqNo=${member.mem_seq_no}'"> 
				<input type="button" value="목록" class="btn btn-default" onclick="location.href='memberList?currentPage=${currentPage}'">
				</td></tr>
		</table>
	</div>
</body>
</html>