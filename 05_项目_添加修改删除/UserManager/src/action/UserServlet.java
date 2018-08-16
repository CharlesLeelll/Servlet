package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

public class UserServlet extends HttpServlet {
	UserService service=new UserService();
	//无参构造
	public UserServlet() {
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
		//设置请求对象中的编码,方便以指定格式编码取出数据,防止乱码
		request.setCharacterEncoding("UTF-8");
		//获得业务类型,选择执行相应代码
		String actionType=request.getParameter("actionType");
		if ("add".equals(actionType)) {//添加操作
			addUser(request, response);
		} else if ("update".equals(actionType)) {//修改操作
			upUser(request, response);
		} else if ("delete".equals(actionType)) {//修改操作
			delUser(request, response);
		} else{
			System.out.println("业务功能匹配失败.....");
		}
		
	}
	//删除用户
	public void delUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String[] param={id};
		int i=service.delete(param);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("删除成功!");
		} else {
			out.print("删除失败!");
		}
		//定时跳转
		response.setHeader("refresh", "3;url=main.jsp");
	}

	//修改用户
	public void upUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");//参数名为 页面标签的name值
		String pass=request.getParameter("pass");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String address=request.getParameter("address");
		String[] param={name,pass,sex,age,address,id};
		int i=service.update(param);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("修改成功!");
		} else {
			out.print("修改失败!");
		}
		
		//定时跳转
		response.setHeader("refresh", "3;url=main.jsp");
	}

	//添加用户
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//服务器将客户端的数据封装到请求对象中,然后调用Servlet方法时,将该请求对象当作为参数传入
		String name=request.getParameter("name");//参数名为 页面标签的name值
		String pass=request.getParameter("pass");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String address=request.getParameter("address");
		String[] param={name,pass,sex,age,address};
		int i=service.insert(param);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (i!=-1) {
			out.print("添加成功!");
		} else {
			out.print("添加失败!");
		}
		
		//定时跳转
		response.setHeader("refresh", "3;url=main.jsp");
	}

	//初始化
	public void init() throws ServletException {
		// Put your code here
	}

}
