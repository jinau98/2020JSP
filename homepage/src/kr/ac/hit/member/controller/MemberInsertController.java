package kr.ac.hit.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;
import kr.ac.hit.member.service.impl.MemberServiceImpl;
import kr.ac.hit.web.servlet.mvc.Controller;

public class MemberInsertController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Member member = new Member();
		
		BeanUtils.populate(member, request.getParameterMap());				//추가한 라이브러리
		
		boolean isError = false;
		
		try {
		MemberService memberService = MemberServiceImpl.getInstance();
		int updCnt = memberService.insertMember(member);
		
		if(updCnt ==0) {
			isError = false;
		}
		}catch(Exception e) {
			e.printStackTrace();
			isError= true;
		}
		
		String viewPage = "redirect:/member/memberList.do";
		
		String message= "정상적으로 회원가입되었습니다.";
		String locationURL = request.getContextPath() + "/member/memberList.do";
		
		if(isError) {
			message = "회원 등록에 실패했습니다.";
			viewPage = "/common/message.jsp";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		
		return viewPage;
	}
	
}
