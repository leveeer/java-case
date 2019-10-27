<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/14
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件下载</title>
</head>
<body>
<form>
    <table border="1" style="margin: auto; text-align: center">
        <tr>
            <th>文件名</th>
            <th>文  件</th>
            <th>文件大小</th>
        </tr>
        <tr>
            <td>${FileForm.fileName}</td>
            <td><a href="fileDown.do">${FileForm.file.fileName}</a></td>
            <td>${FileForm.file.fileSize}</td>
        </tr>
    </table>
</form>
</body>
</html>
