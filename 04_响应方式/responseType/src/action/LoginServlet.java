package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	//无参构造
	public LoginServlet() {
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

	//doPost
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获得账号和密码
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		//然后判断: 正确--->A页面  错误--->B页面
		System.out.println("账号:"+name+"  密码:"+password);
		//假设  TOM/123--->OK  其他--->ON
		if ("tom".equals(name)&&"123".equals(password)) {
			//转发到 SuccessServlet
			request.getRequestDispatcher("SuccessServlet").forward(request, response);//获得转发对象,将请求和响应对象传给下一个组件
		} else {
			//重定向 FailureServlet: 实质 修改浏览器的地址栏为  FailureServlet,访问该地址
			response.sendRedirect("FailureServlet");
		}
	}

	//初始化
	public void init() throws ServletException {
		// Put your code here
	}

}
