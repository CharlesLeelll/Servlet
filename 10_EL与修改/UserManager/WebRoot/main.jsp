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
	<style type="text/css">
	body{
	background-image: url("image/bg2.png");
	}
	#all{
	width: 750px;
	min-height: 350px;
	margin: 0px auto;
	margin-top: 150px;
	padding:20px 0px;
	border: #ccc 2px solid;
	border-radius:10px;
	text-align: center;
	
	background-color: RGBA(236,233,216,0.5);
	}
	#nav *{
	display:inline-black;
	margin: 50px;
	}
	
	#showTable{
	border: 2px blue solid;
	margin: 10px auto;
	width: 550px;
	text-align: center;
	}
	#all a{
	text-decoration: none;
	}
	td,th,tr{
	border: 2px blue solid;
	}
	</style>
	<script type="text/javascript">
	function addUser(){
		var s_w=screen.width;//获得显示器的宽度
		var s_h=screen.height;//获得显示器的高度
		//console.log(s_w+"--->"+s_h);
		var w_w=320; //窗口宽
		var w_h=220; //窗口高
		//计算水平位置: 窗口的左上角的坐标
		var c_w=(s_w-w_w)/2;
		var c_h=(s_h-w_h)/2;
		window.open("addUser.html","","width="+w_w+"px,height="+w_h+"px,top="+c_h+"px,left="+c_w+"px"); 
	}
	function upUser(id){
		var s_w=screen.width;//获得显示器的宽度
		var s_h=screen.height;//获得显示器的高度
		//console.log(s_w+"--->"+s_h);
		var w_w=320; //窗口宽
		var w_h=220; //窗口高
		//计算水平位置: 窗口的左上角的坐标
		var c_w=(s_w-w_w)/2;
		var c_h=(s_h-w_h)/2;
		window.open("UserServlet?actionType=selforup&id="+id,"","width="+w_w+"px,height="+w_h+"px,top="+c_h+"px,left="+c_w+"px"); 
	}
	function delUser(id){ 
		var tf=confirm('您确定要删除吗?');
		if(tf){
		//XMLHttpRequest表示异步请求对象: 可以实现页面不做任何跳转的情况下,向服务器发送请求.
		//创建异步请求对象
		var  XMLHttp=null;
		if(window.ActiveXObject){ //IE
		    XMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
		 }else if(window.XMLHttpRequest){//NetSpace 火狐 欧鹏等
		    XMLHttp = new XMLHttpRequest();
		}
		//创建请求
		XMLHttp.open("post","UserServlet?actionType=delete",true);
		XMLHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//创建回调函数: 
		XMLHttp.onreadystatechange = function () {
		   if (XMLHttp.readyState == 4) { //服务器处理完成,并响应
	            if (XMLHttp.status == 200) {//200表示服务器未报异常: 处理正常
	            	var  i = XMLHttp.responseText;//获得服务器响应的内容
	            	if(i!=-1){
	            		alert("删除成功!");
	            		delTag(id);
	            	}else{
	            		alert("删除失败!");
	            	}
	            }
		   }
		}
		//发送请求
		XMLHttp.send("id="+id);
		}
	}
	function delTag(id){
		var delTr=document.getElementById("t"+id);
		delTr.parentNode.removeChild(delTr);
	}
	</script>
  </head>
  
  <body>
  <div id="all">
    <h1>学生管理系统</h1>
    <span id="nav">
    <span>账号: ${sessionScope.nowUser}</span>
    <a href="javascript:void(0)" onclick="addUser()">添加用户</a>
    <a href="UserServlet?actionType=select">查询用户</a>
    </span>
    
    <table id="showTable">
    <tr><th>ID</th><th>账号</th><th>密码</th><th>性别</th><th>年龄</th><th>地址</th><th>操作</th></tr>
    <!--  requestScope.allUser: list集合    user:Map集合    map.get(键名) -->
    <c:forEach items="${requestScope.allUser }" var="user">
	    <tr id="t${user.id }">
		    <td>${user.id }</td>
		    <td>${user.name }</td>
		    <td>${user.password }</td>
		    <td>${user.sex }</td>
		    <td>${user.age }</td>
		    <td>${user.address }</td>
		    <td><a href="javascript:void(0)" onclick="upUser(${user.id })">修改</a>&nbsp;
			<a href="javascript:void(0)" onclick="return delUser(${user.id })">删除</a></td>
	    </tr>
    </c:forEach>
    </table>
	
   </div>
  </body>
</html>
