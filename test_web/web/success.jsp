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
    <title>登录成功</title>
</head>
<body>
<h1 style="text-align: center">
<%
    out.write("登录成功，3秒后跳转至首页");
    response.setHeader("refresh", "3;URL=index.jsp");
%>
</h1>
</body>
</html>
