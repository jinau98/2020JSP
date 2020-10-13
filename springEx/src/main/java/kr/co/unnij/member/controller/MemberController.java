package kr.co.unnij.member.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/member/memberTest")
	public String memberTest(Model model) throws Exception {
		ArrayList<MemberVO> memberList = memberService.selectMemberTest();
		model.addAttribute("memberList", memberList);
		return "/member/memberTest";
	}
}
