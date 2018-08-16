<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'status.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function closeWindow(){
		//刷新父窗口
		window.opener.location.href="UserServlet?actionType=select";
		//关闭当前窗口
		window.close();
	}
	function autoCloseWindow(){
		window.setTimeout("closeWindow()",3000);
	}
	</script>
  </head>
  
  <body onload="autoCloseWindow()">
    操作${param.i!=1?"失败":"成功" }!3秒后自动关闭! &nbsp;&nbsp;<input type="button" value="关闭" onclick="closeWindow()">
  </body>
</html>
