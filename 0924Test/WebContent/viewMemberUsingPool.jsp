<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
</head>
<body>
<!-- 직접 드라이버를 연결하지 않고 라이브러리로 커넥션 풀(DBCPInit) 만들고 풀을 통해서 드라이버 연결  -->
	MEMBER 테이블의 내용
	<table width="100%" border="0">
		<tr>
			<td>이름</td>
			<td>아이디</td>
			<td>이메일</td>
		</tr>
		<%
			Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String jdbcDriver = "jdbc:apache:commons:dbcp:MEM";
			String query = "select * from TB_MEMBER order by MEM_ID";
			conn = DriverManager.getConnection(jdbcDriver);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
		%>

		<tr>
			<td><%=rs.getString("MEM_NAME")%></td>
			<td><%=rs.getString("MEM_ID")%></td>
			<td><%=rs.getString("MEM_EMAIL")%></td>
		</tr>
		<%
			}
		} catch(SQLException ex){
			out.println(ex.getMessage());
			ex.printStackTrace();
		}finally{
			//사용한 Statement 종료
			try{
				if(rs!=null)rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	%>
	</table>
</body>
</html>