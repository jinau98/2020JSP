package kr.ac.hit.member.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.ac.hit.common.jdbc.ConnectionProvider;
import kr.ac.hit.member.dao.MemberDao;
import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;

public class MemberServiceImpl implements MemberService {
	MemberDao memberDao = MemberDao.getInstance();

	private MemberServiceImpl() {
	}

	private static MemberServiceImpl instance = new MemberServiceImpl();

	public static MemberServiceImpl getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Member> getMemberList(Map<String, Object> paramMap) throws Exception {
		Connection conn = null;
		List<Member> memberList = null;

		try {
			conn = ConnectionProvider.getConnection();
			memberList = memberDao.selectMemberList(conn, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return memberList;

		// 이제 Controller에서는 Service 통해서 Dao의 결과를 받아올 수 있게 됨!
		// controller, dao 등등을 java로 코딩해서 사용하는게 EJB 방식
	}

	@Override
	public Member getMember(Map<String, Object> paramMap) throws Exception {
		Connection conn = null;
		Member member = null;

		try {
			conn = ConnectionProvider.getConnection();
			member = memberDao.selectMember(conn, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return member;
	}

	@Override
	public int insertMember(Member member) throws Exception {
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			updCnt = memberDao.insertMember(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return updCnt;
	}

	@Override
	public int updateMember(Member member) throws Exception {
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			updCnt = memberDao.updateMember(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return updCnt;
	}

	@Override
	public int deleteMember(String seqNo) throws Exception {
		Connection conn = null;
		int updCnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			updCnt = memberDao.deleteMember(conn, seqNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return updCnt;
	}
}