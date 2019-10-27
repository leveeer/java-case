<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/7
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>firstDemo</title>
  </head>
  <body>

  <form action="${pageContext.request.contextPath}/Login.do" method="post">
      用户名：<input type="text" name="username"><br>
      密码：<input type="password" name="password"><br>
      <input type="submit" name="submit" value="登录">
  </form>

  </body>
</html>
