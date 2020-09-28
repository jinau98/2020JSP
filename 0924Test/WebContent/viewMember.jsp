<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>

<%
	String memberId = request.getParameter("memberId");
	System.out.println(memberId);
%>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
	MEMBER 테이블의 내용

	<%
	Class.forName("com.mysql.cj.jdbc.Driver");

Connection conn = null;
Statement stmt = null;
ResultSet rs = null;

try {
	String jdbcDriver = "jdbc:mysql://localhost:3306/jdbc_ex?serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8&useUnicode=true";
	String dbUser = "root";
	String dbPass = "admin";

	String query = "select * from TB_MEMBER where MEM_ID = '" + memberId + "'";

	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

	stmt = conn.createStatement();

	rs = stmt.executeQuery(query);
	//쿼리 실행 결과 출력
	if (rs.next()) {
%>
	<table width="100%" border="1">
		<tr>
			<td>아이디</td>
			<td><%=memberId%></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><%=rs.getString("MEM_PWD")%></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=rs.getString("MEM_NAME")%></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=rs.getString("MEM_EMAIL")%></td>
		</tr>
	</table>
	<%
		} else{
			out.println(memberId + "에 해당하는 정보가 존재하지 않습니다.");
		}
	} catch (SQLException ex) {
	out.println("에러 발생 : " + ex.getMessage());
	} finally {
	//사용한 Statement 종료
	try {
	if (rs != null)
		rs.close();
	if (stmt != null)
		stmt.close();
	if (conn != null)
		conn.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}
	%>
	</table>
</body>
</html>