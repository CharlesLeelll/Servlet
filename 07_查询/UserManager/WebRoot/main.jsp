<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    系统主页. <br>
    <a href="addUser.html">添加用户</a><br>
    <a href="upUser.html">修改用户</a><br>
    <a href="delUser.html">删除用户</a><br>
    <a href="UserServlet?actionType=select">查询用户</a><br>
    
    <table border="2px" bordercolor="blue">
    <tr><th>ID</th><th>账号</th><th>密码</th><th>性别</th><th>年龄</th><th>地址</th></tr>
    <!--  requestScope.allUser: list集合    user:Map集合    map.get(键名) -->
    <c:forEach items="${requestScope.allUser }" var="user">
	    <tr>
		    <td>${user.id }</td>
		    <td>${user.name }</td>
		    <td>${user.password }</td>
		    <td>${user.sex }</td>
		    <td>${user.age }</td>
		    <td>${user.address }</td>
	    </tr>
    </c:forEach>
    </table>
  </body>
</html>
