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

			if (condition != null && !condition.isEmpty()) {
				if ("id".equals(condition.get("searchType"))) { // searchType에 값이 있는지 없는지 체크하고 조건문 실행
					query.append(" AND mem_id = ? "); // pstmt 사용 중이기 때문에 바인딩변수 "?" 사용
				} else if ("name".equals(condition.get("searchType"))) {
					query.append(" AND mem_name like concat ('%', ?, '%')"); // concat은 문장을 이어주는 역할. '%' 와일드카드 - 문장이 여러
																				// 개 있어도 ?이 포함돼있으면 검색해줌
				}
			}

			query.append(" order by mem_seq_no desc");

			PreparedStatement pstmt = conn.prepareStatement(query.toString());

			int i = 1; // 바인딩하는 수
			if (condition != null && !condition.isEmpty()) {
				if ("id".equals(condition.get("searchType"))) {
					pstmt.setString(i++, (String) condition.get("searchWord")); // seachWord는 Object 타입이기 때문에 캐스팅 필요
				} else if ("name".equals(condition.get("searchType"))) {
					pstmt.setString(i++, (String) condition.get("searchWord"));
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

		if (condition != null && !condition.isEmpty()) {
			if (condition.containsKey("mem_seq_no")) {
				query.append(" and mem_seq_no = ? ");
			}
		}

		PreparedStatement pstmt = conn.prepareStatement(query.toString());

		int i = 1;
		if (condition != null && !condition.isEmpty()) {
			if (condition.containsKey("mem_seq_no")) {
				pstmt.setInt(i, (int) condition.get("mem_seq_no"));
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
}
