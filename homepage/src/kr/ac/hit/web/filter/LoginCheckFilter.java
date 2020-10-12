package kr.ac.hit.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	
	private static List<String> excludeURI = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeURI.add("/login/login.jsp");
		excludeURI.add("/login/loginForm.jsp");
		excludeURI.add("/login/logout.jsp");
		excludeURI.add("/login/login.do");
		excludeURI.add("/login/loginForm.do");
		excludeURI.add("/login/logout.do");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		String uri = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if(!excludeURI.contains(uri) && session.getAttribute("LOGIN_USER") ==null) {						//()안의 값이 있는지 없는지 확인하는 contains
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login/loginForm.do");
		}else {
			System.out.println("LoginCheckFilter Session : "+ session.getAttribute("LOGIN_USER"));
			chain.doFilter(request, response);
		}
//		
//		boolean login =false;
//		if(session == null ) {
//			if(session.getAttribute("LOGIN_USER")!= null) {
//				login = true;
//			}
//		}
//		if(login) {
//			chain.doFilter(request, response);
//		} else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/login/loginForm.jsp");
//			dispatcher.forward(request, response);
//		}
	}
	
}
