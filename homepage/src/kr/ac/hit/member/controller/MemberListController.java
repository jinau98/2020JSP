package kr.ac.hit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;
import kr.ac.hit.member.service.impl.MemberServiceImpl;
import kr.ac.hit.web.servlet.mvc.Controller;

public class MemberListController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1005
	
		MemberService memberService = MemberServiceImpl.getInstance();

		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if(searchType != null && searchWord != null) {
			paramMap.put("searchType", searchType);
			paramMap.put("searchWord", searchWord);
		}
		
		List<Member> memberList = memberService.getMemberList(paramMap);
		request.setAttribute("memberList", memberList);
		String viewPage = "/member/memberList.jsp";
		return viewPage;
	}

}
