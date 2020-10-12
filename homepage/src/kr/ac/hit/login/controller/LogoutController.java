package kr.ac.hit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.hit.web.servlet.mvc.Controller;

public class LogoutController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("로그아웃 컨트롤러");
		HttpSession session = request.getSession();

		 if(session.getAttribute("LOGIN_USER") != null){
	         session.removeAttribute("LOGIN_USER");
	      }
	      String viewPage = "/login/loginForm.do";
	      return viewPage;
	   }

	}
	

