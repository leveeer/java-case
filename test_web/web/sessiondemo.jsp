<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/1
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //session.setAttribute("name","zs") ;
    //session.removeAttribute("name") ;
    session.invalidate() ;
%>
<h1>name:${pageScope.name}</h1>
</body>
</html>
