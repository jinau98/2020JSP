<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%

	String path = "/message/notice.txt";
	String realPath = application.getRealPath(path);
	
	InputStream in = application.getResourceAsStream(path);
	BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
%>

	실제 경로 :
	<%=realPath %><br>
	<center>
		<h3>이용약관</h3>

		<% 
	while(true){
	String text = reader.readLine();
	
	if(text==null){
		break;
	}
	
	out.write(text);
	
	}
	%>
	</center>
</body>
</html>