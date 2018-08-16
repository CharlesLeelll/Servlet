package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessServlet extends HttpServlet {

	//无参构造
	public SuccessServlet() {
		super();
	}

	//销毁
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	//doGet
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/*
	 * 执行Servlet类的doGet/doPost,取决于请求方式
	 * 请求方式被封装到请求对象中.
	 * String method=request.getMethod();
	 * 
	 */
	//doPost
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("恭喜您,登陆成功!");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	//初始化
	public void init() throws ServletException {
		// Put your code here
	}

}
