package kr.co.unnij.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import kr.co.unnij.member.model.MemberVO;

public interface MemberService {
	public ArrayList<MemberVO> selectMemberTest() throws Exception;
}
