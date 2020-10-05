package kr.ac.hit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.hit.member.model.Member;

public class MemberDao {
	private MemberDao() {};					//다른데서 생성자 생성 불가.
	
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	public List<Member> selectMemberList(Connection conn) throws Exception{
		List<Member> memberList = new ArrayList<Member>();
		try {
			StringBuffer query = new StringBuffer();
			query.append("select           ");
			query.append(" mem_seq_no      ");
			query.append(", mem_id         ");
			query.append(", mem_name       ");
			query.append(", mem_pwd        ");
			query.append(", mem_birth      ");
			query.append(", mem_phone      ");
			query.append(", mem_email      ");
			query.append(", mem_zipcode    ");
			query.append(", mem_addr_master");
			query.append(", mem_addr_detail");
			query.append(", mem_type       ");
			query.append(" from tb_member  ");
	
			PreparedStatement pstmt = conn.prepareStatement(query.toString());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMem_seq_no(rs.getInt("mem_seq_no"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_pwd(rs.getString("mem_pwd")); 
				member.setMem_birth(rs.getString("mem_birth"));    
				member.setMem_phone(rs.getString("mem_phone"));      
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_zipcode(rs.getString("mem_zipcode"));
				member.setMem_addr_master(rs.getString("mem_addr_master"));
				member.setMem_addr_detail(rs.getString("mem_addr_detail"));
				member.setMem_type(rs.getString("mem_type"));
				
				memberList.add(member);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return null;
	}
}
