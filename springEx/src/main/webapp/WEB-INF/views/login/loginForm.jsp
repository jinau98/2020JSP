<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="UTF-8">
<script type="text/javascript">

$(document).ready(function(){
	$("#mem_id").focus();
	
	$("#mem_pwd").keydown(function(key){
		if(key.keyCode == 13){
			login();
		}
	});
});

	
	function login(){
		alert("login");
		
		var frm = document.loginForm;
	
		if(!validate()){
			return false;
		}
		
		frm.action = "login";
		frm.submit();
	}
	
	
	function validate(){
		var frm = document.loginForm;
		
		if(frm.mem_id.value ==""){
			alert("아이디를 입력하세요");
			return false;
		}
		if(frm.mem_pwd.value == ""){
			alert("패스워드를 입력하세요.");
		}
		return true;
	}
</script>
</head>
<body>
<div id="container" align="center">
	<h3>로그인</h3>
	<form name = "loginForm" id = "loginForm" method = "post">
	<table class="table table-bordered" style="width : 300px">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="mem_id" id="mem_id" size="20"/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mem_pwd" id="mem_pwd" size="20"/></td>
		</tr>
		<tr>
		<td colspan="2"><input type="button" value="로그인" class="btn btn-default"/>
		<input type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/member/memberForm'" value="회원가입"> 
</td>
		</tr>
	</table>
		</form>
</div>
</body>
</html>