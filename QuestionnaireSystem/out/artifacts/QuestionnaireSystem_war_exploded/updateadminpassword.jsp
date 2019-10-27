<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改管理员密码</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改个人密码</h3>
    <form action="${pageContext.request.contextPath}/updateAdminPasswordServlet" method="post">
        <!-- 隐藏域提交id -->
        <input type="hidden" name="id" value="${admin.id}">
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" id="name" name="username" value="${admin.username}" readonly="readonly" placeholder="请输入用户名" />
        </div>

        <div class="form-group">
            <label for="password">原密码：</label>
            <input type="text" class="form-control" id="password"  name="password" value="${admin.password}" readonly="readonly" placeholder="请输入密码" />
        </div>

        <div class="form-group">
            <label for="newpassword">新密码：</label>
            <input type="text" class="form-control" id="newpassword"  name="newpassword" value="${admin.password}" placeholder="请输入新的密码" />
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" onclick="updatePassword(${admin.id}); "/>
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/index.jsp'"/>
        </div>
    </form>
</div>
</body>
<script>
    function updatePassword(id) {
        //用户安全提示
        if(confirm("您确定要修改吗?修改成功后需要重新登录！")){
            //确定,执行删除操作
            location.href="${pageContext.request.contextPath}/updateAdminPasswordServlet?id="+id;
        }
    }
</script>
</html>
