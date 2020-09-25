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
<title>회원 목록</title>
</head>
<body>
	MEMBER 테이블의 내용
	<table width="100%" border="1">
		<tr>
			<td>이름</td>
			<td>아이디</td>
			<td>이메일</td>
		</tr>
		<%
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection conn =null;
			Statement stmt = null;
			ResultSet rs=null;
			
			try{
				String jdbcDriver="jdbc:mysql://localhost:3306/jdbc_ex?serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8&useUnicode=true";
				String dbUser= "root";
				String dbPass="admin";
				
				String query="select * from TB_MEMBER order by MEM_ID";
				
				conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
						
				stmt=conn.createStatement();
				
				rs=stmt.executeQuery(query);
				//쿼리 실행 결과 출력
				while(rs.next()){
					
					
		%>
		<tr>
		<td><%=rs.getString("MEM_NAME") %></td>
		<td><%=rs.getString("MEM_ID") %></td>
		<td><%=rs.getString("MEM_EMAIL") %></td>
		</tr>
		<%
				}
			}catch(SQLException ex){
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