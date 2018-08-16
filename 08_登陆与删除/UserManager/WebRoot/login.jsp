<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <style type="text/css">
    #context{
    width: 300px;
    margin: 300px auto;
    
    }
    </style>
  </head>
  
  <body>
    <div id="context">
    ${empty sessionScope.error?"":sessionScope.error}
    <form action="UserServlet?actionType=login" method="post">
    账号:<input type="text" name="name"><br>
    密码:<input type="password" name="pass"><br>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
    </form>
    </div>
  </body>
</html>
