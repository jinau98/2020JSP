package kr.co.unnij.member.dao;

import java.util.ArrayList;

import kr.co.unnij.member.model.MemberVO;

public interface MemberDAO {
	public ArrayList<MemberVO> selectMemberTest() throws Exception;		//테이블의 전체 받아오기 때문에 arraylist에 담기 
	
}
