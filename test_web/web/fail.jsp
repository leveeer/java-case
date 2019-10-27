<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/22
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录失败</title>
</head>
<body>
<h1 style="text-align: center">
<%
    out.write("登录失败，3秒后跳转至登录界面");
    response.setHeader("refresh", "3;URL=login.jsp");
%>
</h1>
</body>
</html>
