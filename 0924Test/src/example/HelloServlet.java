package example;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=utf-8");
      
      PrintWriter out = resp.getWriter();
      out.write("<html>");
      out.write("<head><title> web.xml 없이 서블릿 쓰기 </title></head>");
      out.write("<body> 안녕하세요, ");
      out.write(req.getParameter("name"));
      out.write("님 </body> </html>");
   }
   
   
}