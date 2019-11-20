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
    <title>修改个人信息</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script>
        function tishi(id) {
            if(confirm("您确定修改吗？修改后将需要重新登录")){
                location.href="${pageContext.request.contextPath}/updatePersonalServlet?id="+id;
            }
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改个人信息</h3>
    <form action="${pageContext.request.contextPath}/updatePersonalServlet" method="post">
        <!-- 隐藏域提交id -->
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" id="name" name="username" value="${user.username}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${user.gender == 'male'}">
                <input type="radio" name="gender" value="male" checked />男
                <input type="radio" name="gender" value="female"  />女
            </c:if>
            <c:if test="${user.gender == 'female'}">
                <input type="radio" name="gender" value="male"  />男
                <input type="radio" name="gender" value="female" checked />女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="tel">电话：</label>
            <input type="text" class="form-control" id="tel" name="tel" value="${user.tel}" placeholder="请输入电话号码"/>
        </div>

        <div class="form-group">
            <label for="email">email：</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" onclick="tishi(${user.id})"/>
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/home.jsp'"/>
        </div>
    </form>
</div>
</body>
</html>
