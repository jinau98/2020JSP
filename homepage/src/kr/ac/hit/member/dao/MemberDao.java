package kr.ac.hit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.hit.member.model.Member;

public class MemberDao {
	private MemberDao() {
	}; // 다른데서 생성자 생성 불가.

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	public List<Member> selectMemberList(Connection conn, Map<String, Object> condition) throws Exception {
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
			query.append(" where 1=1 "); // 전체목록 조회하는 where절

			// if문으로 검색 기능에 사용되는 조건 추가함
			if(condition != null && !condition.isEmpty()) {
	            if(condition.containsKey("mem_seq_no")) {
	               query.append("  AND mem_seq_no = ?");
	            }else if(condition.containsKey("memId")) {
	               query.append("  AND mem_id = ?");
	            }
	            if(condition.containsKey("memPwd")) {
	               query.append("  AND mem_pwd = ?");
	            }
	         }

			PreparedStatement pstmt = conn.prepareStatement(query.toString());

			int i = 1;
			if (condition != null && !condition.isEmpty()) {
				if (condition.containsKey("mem_seq_no")) {
					pstmt.setInt(i++, (int) condition.get("mem_seq_no"));
				}else if(condition.containsKey("memId")) {
					pstmt.setString(i++, (String)condition.get("memId"));
				}
				
				if(condition.containsKey("memPwd")) {
					pstmt.setString(i++, (String)condition.get("memPwd"));
				}
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return memberList;
	}

	// 회원정보 조회
	public Member selectMember(Connection conn, Map<String, Object> condition) throws SQLException {
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
		query.append(" where 1=1 ");

		if(condition != null && !condition.isEmpty()) {
            if(condition.containsKey("mem_seq_no")) {
               query.append("  AND mem_seq_no = ?");
            }else if(condition.containsKey("memId")) {
               query.append("  AND mem_id = ?");
            }
            if(condition.containsKey("memPwd")) {
               query.append("  AND mem_pwd = ?");
            }
         }

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		int i = 1;
		if (condition != null && !condition.isEmpty()) {
			if (condition.containsKey("mem_seq_no")) {
				pstmt.setInt(i++, (int) condition.get("mem_seq_no"));
			}else if(condition.containsKey("memId")) {
				pstmt.setString(i++, (String)condition.get("memId"));
			}
			
			if(condition.containsKey("memPwd")) {
				pstmt.setString(i++, (String)condition.get("memPwd"));
			}
		}

		ResultSet rs = pstmt.executeQuery();

		Member member = null;

		while (rs.next()) {
			member = new Member();
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
		}

		return member;
	}

	public int insertMember(Connection conn, Member member) throws Exception {
		StringBuffer query = new StringBuffer();

		query.append("insert into tb_member "); // seqNo는 자동증가시켜놨기 때문에 insert 하지 않아도 자동 입력됨
		query.append("	(					");
		query.append("	mem_id         		");
		query.append(", mem_name      	 	");
		query.append(", mem_pwd        		");
		query.append(", mem_birth      		");
		query.append(", mem_phone      		");
		query.append(", mem_email      		");
		query.append(", mem_zipcode    		");
		query.append(", mem_addr_master		");
		query.append(", mem_addr_detail		");
		query.append(") values (	   		");
		query.append("?						");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append(", ?					");
		query.append("	)					");
		// (Oracle은 seqNo 사용 시 꼭 다음에 올 숫자를 insert문 안에 지정해줘야 함)

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		int i = 1;
		pstmt.setString(i++, member.getMem_id());
		pstmt.setString(i++, member.getMem_name());
		pstmt.setString(i++, member.getMem_pwd());
		pstmt.setString(i++, member.getMem_birth());
		pstmt.setString(i++, member.getMem_phone());
		pstmt.setString(i++, member.getMem_email());
		pstmt.setString(i++, member.getMem_zipcode());
		pstmt.setString(i++, member.getMem_addr_master());
		pstmt.setString(i++, member.getMem_addr_detail());

		int updCnt = pstmt.executeUpdate();

		return updCnt;
	}

	public int updateMember(Connection conn, Member member) throws Exception {
		StringBuffer query = new StringBuffer();

		query.append("update tb_member set  ");
		query.append(" mem_name=?,       	");
		query.append(" mem_pwd=?,       	");
		query.append(" mem_birth=?,      	");
		query.append(" mem_phone=?,      	");
		query.append(" mem_email=?,     	");
		query.append(" mem_zipcode=?, 	   	");
		query.append(" mem_addr_master=?,	");
		query.append(" mem_addr_detail=?	");
		query.append(" where mem_seq_no =?  ");

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		int i = 1;
		pstmt.setString(i++, member.getMem_name());
		pstmt.setString(i++, member.getMem_pwd());
		pstmt.setString(i++, member.getMem_birth());
		pstmt.setString(i++, member.getMem_phone());
		pstmt.setString(i++, member.getMem_email());
		pstmt.setString(i++, member.getMem_zipcode());
		pstmt.setString(i++, member.getMem_addr_master());
		pstmt.setString(i++, member.getMem_addr_detail());
		pstmt.setInt(i++, member.getMem_seq_no());

		int updCnt = pstmt.executeUpdate();

		return updCnt;
	}
	
	public int deleteMember(Connection conn, String seqNo) throws Exception{
		StringBuffer query = new StringBuffer();
		query.append("delete from tb_member where mem_seq_no=?");
		
		PreparedStatement pstmt = conn.prepareStatement(query.toString());
		
		pstmt.setString(1, seqNo);
		
		int updCnt = pstmt.executeUpdate();
		return updCnt;
	}


}
