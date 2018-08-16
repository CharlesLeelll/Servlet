<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	    function showHiddPass(){
	    	var pass=document.getElementsByName("pass")[0];
	    	var showHiddPass=document.getElementById("showHiddPass");
	    	var passType=pass.type;
	    	if(passType=="text"){
	    		pass.type="password";
	    		showHiddPass.innerHTML="显示密码";
	    	}else{
	    		pass.type="text";
	    		showHiddPass.innerHTML="隐藏密码";
	    	}
	    }
    </script>
  </head>
  
  <body>
    修改用户 <br>
    <!-- 
    requestScope.upUsers 获得 list集合[Map元素]
    requestScope.upUsers[索引] 获得Map集合[键---值]
    requestScope.upUsers[索引].键名   
     -->
    <form action="UserServlet?actionType=update" method="post">
    <input type="hidden" name="id" value="${requestScope.upUsers[0].id }"><br>
    账号:<input type="text" name="name" value="${requestScope.upUsers[0].name }"><br>
    密码:<input type="password" name="pass" value="${requestScope.upUsers[0].password }"><span id="showHiddPass" onclick="showHiddPass()">显示密码</span><br>
    性别:<input type="radio" name="sex" value="男" ${requestScope.upUsers[0].sex=='男'?"checked='checked'":"" } >男
		<input type="radio" name="sex" value="女" ${requestScope.upUsers[0].sex=='女'?"checked='checked'":"" } >女<br>
    年龄:<input type="text" name="age" value="${requestScope.upUsers[0].age }"><br>
    地址:<input type="text" name="address" value="${requestScope.upUsers[0].address }"><br>
    <input type="submit" value="修改">
    <input type="reset" value="重置">
    </form>
  </body>
</html>
