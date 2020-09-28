<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>

<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
		String memId = request.getParameter("memberId");
	String memPw = request.getParameter("password");
	String memName = request.getParameter("name");
	String memEmail = request.getParameter("email");

	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
		String jdbcDriver = "jdbc:mysql://localhost:3306/jdbc_ex?serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8&useUnicode=true";
		String dbUser = "root";
		String dbPass = "admin";

		String query = "insert into TB_MEMBER(MEM_ID, MEM_PWD, MEM_NAME, MEM_EMAIL) values(?,?,?,?)";

		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		pstmt.setString(1, memId);
		pstmt.setString(2, memPw);
		pstmt.setString(3, memName);
		pstmt.setString(4, memEmail);

		int updCnt = pstmt.executeUpdate();

		//쿼리 실행 결과 출력
		if (updCnt == 1) {
	%>
	<table width="100%" border="1">
		<tr>
			<td>아이디</td>
			<td><%=memId%></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><%=memPw%></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=memName%></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=memEmail%></td>
		</tr>
	</table>
	<%
		} else {
	out.println("땡!");
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