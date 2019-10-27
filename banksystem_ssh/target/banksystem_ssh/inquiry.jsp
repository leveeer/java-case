<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/20
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
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
    <title>查询余额</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</head>
<style>
    body{
        background-color: #5bc0de;
    }
</style>
<body>
<div class="container" style="width: 400px;margin-top: 100px">
    <h3 style="text-align: center;">查询余额</h3>
        <div class="form-group">
            <label for="id">用户id：</label>
            <input type="text" class="form-control" id="id" name="id" value="${user.id}" readonly="readonly" />
        </div>
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" id="name" name="username" value="${user.username}" readonly="readonly" />
        </div>

        <div class="form-group">
            <label for="money">余&nbsp;&nbsp;&nbsp;额：</label>
            <input type="text" class="form-control" id="money" name="money" value="${user.money}" readonly="readonly" />
        </div>

        <div class="form-group" style="text-align: center">

            <input class="btn btn-default" type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/userIndex.jsp'"/>
        </div>
</div>
</body>
</html>

