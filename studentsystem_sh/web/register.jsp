<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script src="js/doRegister.js" language="JavaScript"></script>
</head>

<style type="text/css">
    body{
        background-image: url("/images/background.jpg");
    }
</style>
<body>
<div id="register">
	<div class="title">
		<h2>欢迎注册学生管理系统</h2>
	</div>
	<div class="steps">
		<ul class="clearfix">
			<li class="current">1.填写注册信息</li>
			<li class="unpass">2.注册成功</li>
		</ul>
	</div>
	<form method="post" action="${pageContext.request.contextPath}/student.do?command=register" onsubmit="return check()">
		<dl>
			<dt>用 户 名：</dt>
			<dd><input class="input-text" type="text" name="name" id="uid" onblur="checkUser()" /><span id="utip"></span></dd>
			<dt>密　　码：</dt>
			<dd><input class="input-text" type="password" name="password" id="pwd" onblur="checkPwd()" /><span id="ptip"></span></dd>
			<dt>确认密码：</dt>
			<dd><input class="input-text" type="password" name="rePassWord" /><span></span></dd>
            <dt>性    别：</dt>
            <dd><input name="sex" type="radio" value="男" checked/>男
                <input name="sex" type="radio" value="女"/>女
            </dd>
			<dt>Email地址：</dt>
			<dd><input class="input-text" type="text" name="email" id="email" onblur="checkEmail()" /><span id="etip"></span></dd>
			<dt></dt>
			<dd class="button"><input class="input-reg" type="submit" name="register" value="" /></dd>
		</dl>
	</form>
</div>
<div id="footer" class="wrap">
	学生管理系统 &copy; 版权所有

</div>
</body>
</html>
    