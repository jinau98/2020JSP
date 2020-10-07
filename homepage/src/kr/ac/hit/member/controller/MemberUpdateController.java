package kr.ac.hit.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;
import kr.ac.hit.member.service.impl.MemberServiceImpl;
import kr.ac.hit.web.servlet.mvc.Controller;

public class MemberUpdateController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member();
		
		BeanUtils.populate(member, request.getParameterMap());
		
		boolean isError = false;
		
		try {
			MemberService memberService = MemberServiceImpl.getInstance();
			int updCnt = memberService.updateMember(member);
			if(updCnt ==0) {
				isError=true;
			}			
		}catch(Exception e) {
			e.printStackTrace();
			isError = true;
		}
		
		String viewPage = "redirect:/member/memberList.do";
		String message = "회원 수정되었습니다.";
		String locationURL = request.getContextPath()+"/member/memberView.do?seqNo="+member.getMem_seq_no();
		
		if(isError) {
			message = "회원정보 수정에 실패했습니다.";
			viewPage = "/common/message.jsp";
		}
		
		request.setAttribute("isError", isError);
		request.setAttribute("message", message);
		request.setAttribute("locationURL", locationURL);
		return viewPage;
	}
	
}
