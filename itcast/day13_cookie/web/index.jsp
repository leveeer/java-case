<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/4/22
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>
        <%
          /*System.out.println("hello,jsp");
          int i = 5;

          String contextPath = request.getContextPath();
          out.print(contextPath);*/
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                out.write(cookie.getName() + " = " + cookie.getValue());
        %>
        <br>
        <%
            }

        %>
  </h1>
        <%!
          int i = 3;
        %>

        <%= i %>

        <%--<h1>hi,jsp</h1>--%>
  </body>
</html>
