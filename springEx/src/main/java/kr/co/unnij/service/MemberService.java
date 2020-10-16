package kr.co.unnij.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.unnij.member.model.MemberVO;

public interface MemberService {
	public ArrayList<MemberVO> selectMemberTest() throws Exception;
	
	//전체 회원 목록 조회
	public List<MemberVO> getMemberList(Map<String, Object> paramMap) throws Exception;
	
	//전체 회원 수
	public int getMemberCount(Map<String, Object> paramMap) throws Exception;

	public MemberVO getMember(Map<String, Object> paramMap) throws Exception;
	
	public int insertMember(MemberVO member) throws Exception;

	public int updateMember(MemberVO member) throws Exception;
}
