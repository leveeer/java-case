<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

    <style type="text/css">
        body{
            background-image: url("/images/background.jpg");
        }
    </style>
    <script type="text/javascript">

        if(this.location.href != top.location.href) {
            top.location.href = this.location.href;
        }

    function judge() {
            var status = $("input[name='status']:checked").val();
            if (status == 'user'){
                document.form.action="${pageContext.request.contextPath}/student.do?command=login";
            }else {
                document.form.action="${pageContext.request.contextPath}/admin.do?command=login";
            }
        }

    </script>
</head>

<body>
<div id="login">
    <h1 align="center">学生管理系统</h1>
    <h2>登录</h2>
    <form name="form" method="post" action="">
        <dl>
            <dt>用户名：</dt>
            <dd><input class="input-text" type="text" name="name" id="name" onblur="checkname()"/><span id="mess"></span></dd>
            <dt>密　码：</dt>
            <dd><input class="input-text" type="password" name="password"/><span></span></dd>
            <dt>身　份：</dt>
            <dd><input name="status" type="radio" value="user" checked/>用户 &nbsp;&nbsp;
                 <input name="status" type="radio" value="admin"/>管理员
            </dd>
            <dd class="button">
                <input id="login_submit" class="input-btn" type="submit" name="submit" value="" onclick="judge()"/>
                <input class="input-reg" type="button" name="register" value="" onclick="window.location='register.jsp';"/></dd>
        </dl>
    </form>
    <div  style="text-align: center; color: red"><span>${login_error}</span></div>
</div>

<div id="footer" class="wrap">
    学生管理系统 &copy; 版权所有

</div>
</body>

</html>