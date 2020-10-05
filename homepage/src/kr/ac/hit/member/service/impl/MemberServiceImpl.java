package kr.ac.hit.member.service.impl;

import java.sql.Connection;
import java.util.List;

import kr.ac.hit.common.jdbc.ConnectionProvider;
import kr.ac.hit.member.dao.MemberDao;
import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;

public class MemberServiceImpl implements MemberService{
	MemberDao memberDao = MemberDao.getInstance();
	
	private MemberServiceImpl() {}
	
	private static MemberServiceImpl instance = new MemberServiceImpl();
	
	public static MemberServiceImpl getInstance() {
		if(instance ==null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<Member> getMemberList() throws Exception{
		Connection conn = null;
		List<Member> memberList = null;
		
		try {
		conn = ConnectionProvider.getConnection();
		memberList = memberDao.selectMemberList(conn);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			conn.close();
		}
		return memberList;
		
		//이제 Controller에서는 Service 통해서 Dao의 결과를 받아올 수 있게 됨!
		//controller, dao 등등을 java로 코딩해서 사용하는게 EJB 방식
	}
}
