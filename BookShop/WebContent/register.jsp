<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/doRegister.js" language="JavaScript"></script>
</head>
<body>
<%@include file="menu.jsp" %>
<div id="register">
	<div class="title">
		<h2>欢迎注册成人教育网上书城</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form method="post" action="register.action" onsubmit="return check()">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" name="userName" id="uid" onblur="checkUser()" /><span id="utip"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" name="passWord" id="pwd" onblur="checkPwd()" /><span id="ptip"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" name="rePassWord" /><span></span></dd>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" name="email" id="email" onblur="checkEmail()" /><span id="etip"></span></dd>
			<dt></dt>
			<!--通过隐藏域告诉服务器需要的执行操作  -->
			<input  type="hidden" name="cmd" value="register"/>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	成人教育网上书城 &copy; 版权所有

</div>
</body>
</html>
    