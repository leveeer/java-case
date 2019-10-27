<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/22
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath() + "/productServlet/index");
%>
</body>
</html>
