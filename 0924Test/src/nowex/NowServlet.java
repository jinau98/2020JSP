package nowex;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NowServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		Date now = new Date();
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>서블릿 예제</title></head>");
		writer.println("<body>");
		writer.println("현재 시간 : ");
		writer.println(now);
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
}

