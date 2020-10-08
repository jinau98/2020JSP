package kr.ac.hit.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hit.web.servlet.mvc.Controller;

public class LoginFormController implements Controller{


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewPage = "/login/loginForm.jsp";
		return viewPage;
	}
	
}
