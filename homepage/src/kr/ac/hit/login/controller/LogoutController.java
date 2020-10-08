package kr.ac.hit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.hit.web.servlet.mvc.Controller;

public class LogoutController implements Controller{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();

		request.setCharacterEncoding("utf-8");
		String message = null;
		System.out.println("로그아웃 전 : " + session.getAttribute("LOGIN_USER"));
		if (session.getAttribute("LOGIN_USER") != null) {
			session.removeAttribute("LOGIN_USER");
		}
		System.out.println("로그아웃 후 : " + session.getAttribute("LOGIN_USER"));
		
		String viewPage = "/login/loginForm.jsp";
		return viewPage;
	}
	
}
