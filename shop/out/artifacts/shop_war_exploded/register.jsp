<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
<script type="text/javascript">
    window.onload = function () {
        document.getElementById("form").onsubmit = function () {
            return checkUsername() && checkPassword() && checkRepassword()
            && checkEmail() && checkName() && checkBirthday();
        };

        document.getElementById("username").onblur = checkUsername;
        document.getElementById("password").onblur = checkPassword;
        document.getElementById("repassword").onblur = checkRepassword;
        document.getElementById("email").onblur = checkEmail;
        document.getElementById("name").onblur = checkName;
        document.getElementById("date").onblur = checkBirthday;
    };

    function checkUsername() {
        var username = document.getElementById("username").value;
        var u_tip = document.getElementById("u_tip");
        if (username == ""){
            u_tip.innerHTML="用户名不能为空";
            return false;
        }else {
            u_tip.innerHTML="";
            return true;
        }
    }

    function checkPassword() {
        var password = document.getElementById("password").value;
        var p_tip = document.getElementById("p_tip");
        if (password == ""){
            p_tip.innerHTML="密码不能为空";
            return false
        }
        if (password.length < 6 || password.length > 15){
            p_tip.innerHTML="密码格式不符";
            return  false;
        }else {
            p_tip.innerHTML="";
            return true;
        }
    }

    function checkRepassword() {
        var password = document.getElementById("password").value;
        var repassword = document.getElementById("repassword").value;
        var rp_tip = document.getElementById("rp_tip");
        if (password != repassword){
            rp_tip.innerHTML="两次输入的密码不一致";
            return false;
        }else {
            rp_tip.innerHTML="";
            return true;
        }
    }

    function checkEmail(){
        var email=document.getElementById("email").value;
        var e_tip=document.getElementById("e_tip");
        if(email.indexOf("@")==-1){
            e_tip.innerHTML="邮箱必须包含@";
            return false;
        }
        else if(email.indexOf(".")<=email.indexOf("@")){
            e_tip.innerHTML="邮箱必须包含@.";
            return false;
        }else {
            e_tip.innerHTML="";
            return true;
        }

    }

    function checkName() {
        var name = document.getElementById("name").value;
        var n_tip = document.getElementById("n_tip");
        if (name == ""){
            n_tip.innerHTML="用户名不能为空";
            return false;
        }else {
            n_tip.innerHTML="";
            return true;
        }
    }

    function checkBirthday() {
        var birthday = document.getElementById("date").value;
        var d_tip = document.getElementById("d_tip");
        if (birthday == ""){
            d_tip.innerHTML="生日不能为空";
            return false;
        } else {
            d_tip.innerHTML="";
            return true;
        }
    }
</script>

</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container" style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8" style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<form id="form" class="form-horizontal" style="margin-top: 5px;" action="${pageContext.request.contextPath}/register" method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                            <span id="u_tip" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                            <span id="p_tip" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="repassword" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="repassword" placeholder="请输入确认密码">
                            <span id="rp_tip" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱账号">
                            <span id="e_tip" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                            <span id="n_tip" style="color: red"></span>
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio1" value="male" checked>男</label>
                            <label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio2" value="female">女</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" id="date" class="form-control" name="birthday">
                            <span id="d_tip" style="color: red"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="checkCode" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" id="checkCode" class="form-control" name="checkCode" placeholder="请输入验证码">
						</div>
						<div class="col-sm-2">
							<img src="${pageContext.request.contextPath}/image/captcha.jhtml" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>
