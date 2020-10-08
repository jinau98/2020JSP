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
		
		
		String message = null;
		if(member != null){
			if(mem_pwd.equals(member.getMem_pwd())){
				session.setAttribute("LOGIN_USER", member);					//member로 불러온 비밀번호와 입력된 비밀번호가 맞으면 세션에 저장
			}else{
				message="잘못된 비밀번호입니다.";
			}
		}else{
			message="존재하지 않는 아이디입니다.";
		}

		String viewPage = "/member/memberList.jsp";
		return viewPage;
	}
	
}
