package kr.co.unnij.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.unnij.member.dao.MemberDAO;
import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDao;	
	
	public ArrayList<MemberVO> selectMemberTest() throws Exception {
		return memberDao.selectMemberTest();
	}

	@Override
	public List<MemberVO> getMemberList(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.selectMemberList(paramMap);
	}

	@Override
	public int getMemberCount(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.selectMemberCount(paramMap);
		
	}
	@Override
	public MemberVO getMember(Map<String, Object> paramMap) throws Exception{
		return memberDao.selectMember(paramMap);
	}

	@Override
	public int insertMember(MemberVO member) throws Exception {
		return memberDao.insertMember(member); 
	}

	@Override
	public int updateMember(MemberVO member) throws Exception {
		return memberDao.updateMember(member);
	}

	@Override
	public int deleteMember(int seqNo) throws Exception {
		return memberDao.deleteMember(seqNo);
	}
}
