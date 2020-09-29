package mvc.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class TestHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("test", "testtest");
		return "/WEB-INF/view/test.jsp";
	}
}
