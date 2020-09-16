<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
<script type="text/javascript">
function vcheck(){
	var lf = document.loginForm;
	if(lf.memId.value == " "){
		alert("아이디를 입력하세요~");
		return false;
	}
	if(lf.memPw.value==" "){
		alert("비밀번호를 입력하세요~");
		return false;
	}
}
</script>
</head>
<body>
<b>로그인</b>
<form action="/0914Test/loginCheck.jsp" method="post" name="loginForm" onsubmit="return vcheck();">
		아이디 : <input type="text" name="memId"><br> 
		패스워드 : <input type="password" name="memPw"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>