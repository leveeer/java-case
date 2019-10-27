<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/14
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%--常用注解--%>
        <%--<a href="anno/testRequestParam?username=zs">RequestParam注解</a>--%>

        <%--<form action="anno/testRequestBody" method="post">
            姓 名：<input type="text" name="username"><br>
            年 龄：<input type="text" name="age"><br>
            <input type="submit" value="提交">
        </form>--%>

        <a href="anno/testPathVariable/100">PathVariable注解</a><br>

        <a href="anno/testCookieValue">CookieValue注解</a><br>

        <form action="anno/testModelAttribute" method="post">
            姓 名：<input type="text" name="uname"><br>
            年 龄：<input type="text" name="age"><br>
            <input type="submit" value="提交">
        </form>

        <a href="anno/setSessionAttributes">设置Session</a><br>

        <a href="anno/getSessionAttributes">获取Session</a><br>

        <a href="anno/deleteSessionAttributes">删除Session</a><br>
</body>
</html>
