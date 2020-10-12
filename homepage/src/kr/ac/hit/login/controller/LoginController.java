package kr.ac.hit.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.hit.member.model.Member;
import kr.ac.hit.member.service.MemberService;
import kr.ac.hit.member.service.impl.MemberServiceImpl;
import kr.ac.hit.web.servlet.mvc.Controller;

public class LoginController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");

		MemberService memberService = MemberServiceImpl.getInstance();

		Map<String, Object> condition = new HashMap<String, Object>();
		if(mem_id!= null && mem_pwd != null){
			condition.put("memId", mem_id);
			condition.put("memPwd", mem_pwd);
		}

		Member member = memberService.getMember(condition);
		
		
		String viewPage = "";
		String message = "";
		boolean isError = false;
		
	    String locationURL = request.getContextPath() + "/member/memberList.do";

		if(member != null){
			 if(mem_pwd.equals(member.getMem_pwd())){
		         message = "로그인 성공!!";
		         session.setAttribute("LOGIN_USER", member);
		         viewPage = "/common/message.jsp";
		         isError = false;
		         }
		}else{
			 message = "해당 정보 존재하지 않습니다!";
	         isError = true;
		}

		 request.setAttribute("message", message);
	     request.setAttribute("locationURL", locationURL);
	     request.setAttribute("isError", isError);
	      
	     return viewPage;

	}
	
}
