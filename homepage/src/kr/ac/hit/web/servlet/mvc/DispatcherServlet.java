package kr.ac.hit.web.servlet.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.hit.web.servlet.mvc.handler.UrlHandlerMapping;

public class DispatcherServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		String contextConfigLocation = getInitParameter("contextConfigLocation");
		String configFilePath = getServletContext().getRealPath(contextConfigLocation);
		try {
			UrlHandlerMapping.init(configFilePath);			//초기화
		} catch (Exception e) {
			throw new ServletException();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("==============요청 URI=============");
		System.out.println(uri);
		//localhost:8080/rootContext/member/memberList ~> memberList만 남기기 위한 작업
		if(uri.indexOf(req.getContextPath()) == 0) {
			uri = uri.substring(req.getContextPath().length());
		}
		System.out.println(uri);
		
		// memberListController의 인스턴스 받아오기
		Controller controller = UrlHandlerMapping.getHandler(uri);
		
		if(controller != null) {
			String viewPage = null;
			try {
				viewPage = controller.process(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
			
			if(viewPage != null) {
				if(viewPage.startsWith("redirect:")) {
					viewPage = viewPage.substring("redirect:".length());
					resp.sendRedirect(req.getContextPath()+viewPage);
				}else {
					RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
					dispatcher.forward(req, resp);
				}
			}
		}else {
			//404에러
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);				//전체 다 대문자 => 상수
		}
	}
}
