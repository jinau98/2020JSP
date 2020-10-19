package kr.co.unnij.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.unnij.member.model.MemberVO;
import kr.co.unnij.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService memberService;		//autowired 하면 멤버서비스를 찾아서 객체를 알아서 찾아주고 임플에 있는 메서드를 알아서 실행함
	@Inject
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/login/loginForm")
	public String loginform() throws Exception {
		return "login/loginForm";
	}
	
	@RequestMapping("/login/login")
	public String login(@RequestParam(value="mem_id", required = true) String mem_id,
						@RequestParam(value="mem_pwd", required=true) String mem_pwd, 
						Model model, HttpSession session) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mem_id", mem_id);
		
		MemberVO resultMember = memberService.getMember(paramMap);
		boolean isError = true;
		String message = "";
		
		if(resultMember == null) {
			//아이디 없음
			message="회원정보가 없습니다.";
			isError= true;
		}else {
			boolean isCheck =  passwordEncoder.matches(mem_pwd, resultMember.getMem_pwd());
			
			if(isCheck) {
				isError=false;
			}else {
				isError=true;
			}
		}
		
		if(isError) {
			isError = true;
			message = "회원정보가 없습니다.";
		}else {
			isError = false;
			message=resultMember.getMem_name() + "님 환영합니다";
			session.setAttribute("LOGIN_USER", resultMember);
			model.addAttribute("locationURL", "/member/memberList");
		}
		
		model.addAttribute("isError", isError);
		model.addAttribute("message", message);
		
		return "common/message";
	}
	
	@RequestMapping(value="/login/logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
//		session.removeAttribute("LOGIN_USER");
//		session.setAttribute("LOGIN_USER", null);
		
		return "redirect:/";
	}
	

}
