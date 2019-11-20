<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/14
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%--<form action="/file/fileUpload" method="post" enctype="multipart/form-data">
            头像：<input type="file" name="file"><br>
            <input type="submit" value="上传">

        </form>--%>

        <form action="/file/springmvcFileUpload" method="post" enctype="multipart/form-data">
            头像：<input type="file" name="file"><br>
            <input type="submit" value="上传">

        </form>
</body>
</html>
