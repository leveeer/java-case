<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/16
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <center>
        <form action="${pageContext.request.contextPath}/user/login.do" method="post">
            用户名：<input name="username" type="text"><br>
            密  码：<input name="password" type="password"><br>
            <input type="submit" value="登录">
        </form>
    </center>
</body>
</html>
