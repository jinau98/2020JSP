package kr.co.unnij.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.unnij.common.util.PagingUtil;
import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Controller
@RequestMapping(value = "/member") // 메서드들에 공통적으로 사용하는 url 뽑아서 매핑
public class MemberController { // requestmapping 이용해서 하나의 컨트롤러 만으로 구분 가능 (EJB 방식에서는 다 각자 만들어줘야 했음)
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/memberTest")
	public String memberTest(Model model) throws Exception {
		ArrayList<MemberVO> memberList = memberService.selectMemberTest();
		model.addAttribute("memberList", memberList);
		return "/member/memberTest";
	}

	@RequestMapping(value="/memberList")
	public String memberList(
			@RequestParam(value="searchType", required=false, defaultValue = "") String searchType,
			@RequestParam(value="searchWord", required=false, defaultValue = "") String searchWord,
			@RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
			@RequestParam(value="pageSize", required = false, defaultValue = "10") int pageSize,
			Model model) throws Exception {
		
		int pageCount = 5;
		int totalCount = 0;
		
		List<MemberVO> memberList = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		 if(!StringUtils.isBlank(searchType) && !StringUtils.isBlank(searchType)) {
	         paramMap.put("searchType", searchType);
	         paramMap.put("searchWord", searchWord);
	      }

		
		totalCount = memberService.getMemberCount(paramMap);
		
		PagingUtil pagingUtil = new PagingUtil(currentPage, totalCount, pageSize, pageCount);		//currentPage, pageSize 는 받아올 것(RequestParam)
		
		paramMap.put("startRow", pagingUtil.getStartRow());
		paramMap.put("endRow", pagingUtil.getEndRow());
		
		memberList = memberService.getMemberList(paramMap);
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagingUtil", pagingUtil);
		
		return "/member/memberList";			//리턴하고자 하는 jsp의 위치
	}

	@RequestMapping(value="/memberView")
	public String memberView(@RequestParam(value="seqNo", required=true) int seqNo, @RequestParam(value="currentPage", required=true) int currentPage, Model model) throws Exception{
		Map<String, Object> paramMap= new HashMap<String, Object>();
		paramMap.put("mem_seq_no", seqNo);
		MemberVO member = memberService.getMember(paramMap);
		model.addAttribute("member", member);
		model.addAttribute("currentPage", currentPage);
		return "/member/memberView";
	}
	}
