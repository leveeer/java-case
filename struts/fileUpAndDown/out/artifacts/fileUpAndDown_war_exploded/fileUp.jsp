<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/14
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
<table border="1" style="margin: auto">
    <form id="form" action="fileUp.do" method="post" enctype="multipart/form-data">
        <tr>
            <td>文件名：</td>
            <td><input type="text" name="fileName"></td>
        </tr>
        <tr>
            <td>文件:</td>
               <td> <input type="file" name="file"></td>
        </tr>
        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>

    </form>
</table>

  </body>
</html>
