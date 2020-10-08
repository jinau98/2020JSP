<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
	<script>
	function login(){
		var frm = document.loginForm;
		if(frm.mem_id.value==""){
			alert("아이디를 입력하세요.");
			return false;
		}
		if(frm.mem_pwd.value ==""){
			alert("패스워드를 입력하세요");
			return false;
		}
		frm.action="login.do";
		frm.submit();
	}
	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="loginForm" method="post">
		<table align="center" class="table table-bordered" style="width: 300px">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mem_id" size="20"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="mem_pwd" size="20"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="로그인"
					class="btn btn-default" onclick="login();"></td>
		</table>
	</form>
</body>
</html>