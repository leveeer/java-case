<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录页面</title>
    <meta name="author" content="DeathGhost"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_login.css"
          tppabs="${pageContext.request.contextPath}/css/style_login.css"/>
    <style>
        body {
            height: 100%;
            background: #426785;
            overflow: hidden;
        }

        canvas {
            z-index: -1;
            position: absolute;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/verificationNumbers.js"
            tppabs="js/verificationNumbers.js"></script>
    <script src="${pageContext.request.contextPath}/js/Particleground.js" tppabs="js/Particleground.js"></script>
    <style>
        .alert {
            font-size: 20px;
            margin-top: 20px;
            text-align: center;
            display: block;
            color: red;
        }
    </style>
    <script>
        $(document).ready(function () {
            //粒子背景特效
            $('body').particleground({
                dotColor: '#2adcd2',
                lineColor: '#2adcd2'
            });
        });

        function judge() {
            var status = $("input[name='status']:checked").val();
            var form = document.forms['form'];
            if (status == 'user') {
                form.action = "${pageContext.request.contextPath}/user/login.do";
                form.submit();
            } else {
                form.action = "${pageContext.request.contextPath}/admin/login.do";
                form.submit();
            }
        }
    </script>
</head>
<body>
<form name="form" action="" method="post">

    <dl class="admin_login">

        <dt>
            <strong style="font-size: 26px">银 行 系 统</strong>
            <em>Bank System</em>
        </dt>

        <dd class="user_icon">
            <input type="text" name="username" placeholder="用户名" class="login_txtbx"/>
        </dd>

        <dd class="pwd_icon">
            <input type="password" name="password" placeholder="密 码" class="login_txtbx"/>
        </dd>

        <center>
            <dd style="margin-top: 30px; color: #e6ebf0; font-size: 16px">身　份：
                <input name="status" type="radio" value="user" checked/>用户 &nbsp;&nbsp;
                <input name="status" type="radio" value="admin"/>管理员
            </dd>
        </center>

        <dd>
            <input style="margin-left: 20px" type="button" value="立即登陆" class="submit_btn" onclick="judge()"/>
            <input style="margin-left: 20px" type="button" value="立即注册" class="submit_btn"
                   onclick="window.location='register.jsp'"/>
        </dd>
        <!-- 用户名或密码错误提示 -->
        <div class="alert" role="alert">
            <span>${login_error}</span>
        </div>
    </dl>

</form>

</body>
</html>
