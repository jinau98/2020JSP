package kr.co.unnij.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);				//false인 경우 null 발생하므로 nullpointcheck 하기
		
		if(session == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);				//403 에러 발생
			return false;
		}		
		
		if(session.getAttribute("LOGIN_USER")== null) {
			response.sendRedirect(request.getContextPath() + "/login/loginForm");
		}
		return true;
	}
	
}
