<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/22
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>

</head>
<body>

<%
    String username = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        //System.out.println(cookie.getName() + " = " + cookie.getValue());
        if (cookie.getName().equals("zs") && cookie.getValue().equals("123")) {
            username = cookie.getName();
            password = cookie.getValue();
        }
    }
    //System.out.println("===========================");
%>
<form style="text-align: center" action="check.jsp" method="post">
    用户名:<input  type="text" value="<%=username%>" name="username" ><br>
    密  码:<input type="password" value="<%=password%>" name="password"><br>
    记住密码:<input type="checkbox" name="checkbox" value="flag"><br>
    <input type="submit" value="登录">
</form>

</body>

</html>
