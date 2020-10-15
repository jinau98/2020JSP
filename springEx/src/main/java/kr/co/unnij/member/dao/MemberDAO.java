package kr.co.unnij.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.co.unnij.member.model.MemberVO;

public interface MemberDAO {
	public ArrayList<MemberVO> selectMemberTest() throws Exception;		//테이블의 전체 받아오기 때문에 arraylist에 담기 
	
	//tb_member 테이블의 전체 갯수
	public int selectMemberCount(Map<String, Object> paramMap) throws Exception;
	
	//회원전체목록
	public List<MemberVO> selectMemberList(Map<String, Object> paramMap) throws Exception;
	
	//회원정보 조회(1건)
	public MemberVO selectMember(Map<String, Object> paramMap) throws Exception;
	
	//회원정보 등록
	//회원정보 수정
	//회원정보 삭제
}
