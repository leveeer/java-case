<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
//var xhr=new XMLHttpRequest();
function getajaxHttp() {
var xmlHttp;
try {
// Firefox, Opera 8.0+, Safari 
        xmlHttp = new XMLHttpRequest();
       } catch (e) {
        // Internet Explorer 
        try {
          xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
          try {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
          } catch (e) {
            alert("您的浏览器不支持AJAX！");
            return false;
          }
        }
      }
      return xmlHttp;
    }
       function checkname(){
	  var name=document.getElementById("name").value;
	  ajaxRequest(name);
	
    }
     //设置ajax请求的函数
       function ajaxRequest(name){
       	var xhr=getajaxHttp();
       	//匿名函数
       	xhr.onreadystatechange=function(){
       		var mess=document.getElementById("mess");
       		if(xhr.readyState==4&&xhr.status==200){
       			if(xhr.responseText=="true"){
       				mess.innerHTML="";
       			}else{
       				mess.innerHTML="<font color='red'>用户名不存在</font>";
       			}
       			
       		}
       		
       	}
       	//设置请求参数
       	xhr.open("POST","validata?name="+name,true);
       	//发送请求
       	xhr.send(null);
       }
</script>
</head>
<body>
<%@include file="menu.jsp" %>
<div id="login">
	<h2>用户登陆</h2>
	<form method="post" action="login.action">
		<dl>
			<dt>用户名：</dt>
			<dd><input class="input-text" type="text" name="userName" id="name" onblur="checkname()"/><span id="mess"></span></dd>
			<dt>密　码：</dt>
			<dd><input class="input-text" type="password" name="passWord" /><span></span></dd>
			<dt>验证码：</dt>
			<dd><input class="input-text" type="text" name="validationCode" /><span></span></dd>
			&nbsp<img src="${pageContext.request.contextPath}/ValidationCodeServlet" >
			<input type="button" value="看不清? 换一张" id="btn">
			<dt></dt>
			<!--通过隐藏域告诉服务器需要的执行操作  -->
			<input  type="hidden" name="cmd" value="login"/>
			<dd class="button"><input class="input-btn" type="submit" name="submit" value="" /><input class="input-reg" type="button" name="register" value="" onclick="window.location='register.jsp';" /></dd>
		</dl>
	</form>
</div> 
<script type="text/javascript">
    document.getElementById("btn").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[1].src ="${pageContext.request.contextPath}/ValidationCodeServlet?time=" + new Date().getTime();
    };
</script>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有

</div>
</body>
</html>
    