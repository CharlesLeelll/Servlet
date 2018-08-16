<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function show() {
			// 获取img标签
			var img = document.getElementById("imge");
			// 改变属性值src
			img.src = "/webcode/CodeServlet?"+new Date().getTime();// 获取时间毫秒数
		}
	
	</script>
	
	
  </head>
  <!-- 将验证码内存字节数据写入到jsp程序中.
  如何实现?
  	向jsp发送一张图片
  	
  	当jsp页面表中请求一个动态资源后,是可以将内容写入到标签中的.
  	注意:超链接标签href属性值为null时,点击效果相当于刷新
   -->
  
  <body>
  姓名:
    <img alt="" src="/webcode/CodeServlet"  id="imge">
    <a href="javaScript:show()">看不清</a>
  </body>
</html>
