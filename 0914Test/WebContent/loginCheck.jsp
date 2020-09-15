<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   String id = request.getParameter("memId");
   String pw = request.getParameter("memPw");
   if(id!=null && id.equals("jinu") && pw!=null && pw.equals("1234")){
	   response.sendRedirect("/0914Test/loginSuccess.jsp?memId="+id);
   }else{
	  response.sendRedirect("/0914Test/memberForm.jsp");
   }
	   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>

</body>
</html>
