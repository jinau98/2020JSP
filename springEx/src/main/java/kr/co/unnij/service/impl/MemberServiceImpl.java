package kr.co.unnij.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.unnij.member.dao.MemberDAO;
import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO memberDao;
	
	public ArrayList<MemberVO> selectMemberTest() throws Exception{
		return memberDao.selectMemberTest();
	}
}
