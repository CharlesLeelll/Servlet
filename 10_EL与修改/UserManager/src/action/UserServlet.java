package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

		/*response.setContentType("text/html");
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
		out.close();*/
		
		doPost(request, response);
		
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
		} else if ("delete".equals(actionType)) {//删除操作
			delUser(request, response);
		} else if ("select".equals(actionType)) {//查询所有操作
			selUser(request, response);
		} else if ("login".equals(actionType)) {//登陆操作
			login(request, response);
		} else if ("selforup".equals(actionType)) {//查询要修改的数据,并存入容器,然后跳转到修改页面
			selUserForUp(request, response);
		} else{
			System.out.println("业务功能匹配失败.....");
		}
		
	}
	//查询要修改的用户
	public void selUserForUp(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id=request.getParameter("id");//获得被修改数据的id
			String sql="select * from user where id=?";
			List<Map<String,String>> list=service.select(sql,new String[]{id});//查询要修改的数据
			request.setAttribute("upUsers", list);//将数据存入容器
			
			request.getRequestDispatcher("upUser.jsp").forward(request, response);//跳转到修改页面,然后取出容器的数据,显示给用户
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//登陆
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		//Servlet--->JSP[Servlet]: 容器
		try {
			String sql="select * from user where name=? and password=?";
			List<Map<String,String>> list=service.select(sql,new String[]{name,pass});
			if (list!=null) {
				//取出查询结果,匹配输入的信息
				Map<String,String> map=list.get(0);
				if (name.equals(map.get("name")) && pass.equals(map.get("password")) ) {
					//一切正常---主页面
					request.getSession().setAttribute("nowUser", name);
					response.sendRedirect("main.jsp");
					return;
				} 
			} 
			
			request.getSession().setAttribute("error", "登录失败,请重试!");
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//查询用户
	public void selUser(HttpServletRequest request,
			HttpServletResponse response) {
		//Servlet--->JSP[Servlet]: 容器
		try {
			String sql="select * from user";
			List<Map<String,String>> list=service.select(sql,new String[0]);
			request.setAttribute("allUser", list);
			
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//删除用户
	public void delUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		String[] param={id};
		int i=service.delete(param);
		
		//响应异步请求
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(i);
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
		
		response.sendRedirect("status.jsp?i="+i);
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
		response.sendRedirect("status.jsp?i="+i);
	}

	//初始化
	public void init() throws ServletException {
		// Put your code here
	}

}
