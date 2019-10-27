<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/20
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>取款</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        .alert {
            margin-top: 20px;
            text-align: center;
            display: block;
            color: red;
        }

        body {
            background-color: #426785;
            color: white;
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            //1.给表单绑定onsubmit事件
            document.getElementById("form").onsubmit = function () {
                return checkMoney();
            };

            //给金额输入框绑定离焦事件
            document.getElementById("money").onblur = checkMoney;
        };

        function checkMoney() {
            var money = document.getElementById("money").value;
            var withdraw_error = document.getElementById("withdraw_error");
            if (money == "") {
                withdraw_error.innerHTML = "取款金额不能为空";
                return false;
            }
            if (money < 0) {
                withdraw_error.innerHTML = "取款金额不能为负数";
                return false;
            }
            if (money == 0) {
                withdraw_error.innerHTML = "取款金额不能为0";
                return false;
            }

            var regex = /^-?\d+(\.\d+)?$/;
            var flag = regex.test(money);
            if (!flag) {
                withdraw_error.innerHTML = "金额输入有误";
            } else {
                withdraw_error.innerHTML = "";
            }
            return flag;
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;margin-top: 100px">
    <h3 style="text-align: center;">取&nbsp;&nbsp;&nbsp;款</h3>
    <form id="form" action="${pageContext.request.contextPath}/user/withdraw.do?id=${user.id}" method="post">
        <div>
            <label for="id">用户id：</label>
            <input type="text" class="form-control" id="id" name="id" value="${user.id}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" id="name" name="username" value="${user.username}"
                   readonly="readonly"/>
        </div>

        <div class="form-group">
            <label for="money">取款金额：</label>
            <input type="text" class="form-control" id="money" name="money" placeholder="请输入取款金额"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回" onclick="window.location='../pages/userIndex.jsp'"/>
        </div>
    </form>
    <!-- 错误提示 -->
    <div class="alert" role="alert">
        <span id="withdraw_error">${withdraw_error}</span>
    </div>
</div>
</body>
</html>