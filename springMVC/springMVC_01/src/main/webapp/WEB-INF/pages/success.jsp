<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/13
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h3>hello springMVC</h3>
        <h4>${requestScope.msg}</h4>
        <h4>${sessionScope.msg}</h4>
        <h4>${user.uname}</h4>

</body>
</html>
