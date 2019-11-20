<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/14
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改密码</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        .alert{
            margin-top: 20px;
            text-align: center;
            display: block;
            color: red;
        }
        body{
            background-color: #5bc0de;
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            //1.给表单绑定onsubmit事件
            document.getElementById("form").onsubmit = function () {
                if (confirm("您确定要修改密码吗？修改后将需要重新登录")){
                    //调用密码校验方法   checkNewPwd();
                    return checkNewPwd();
                }


            };
            //给密码框绑定离焦事件
            document.getElementById("newPwd").onblur = checkNewPwd;
        };
        function checkNewPwd() {
            var newPwd = document.getElementById("newPwd").value;
            var tip = document.getElementById("tip");
            if (newPwd == "") {
                tip.innerHTML = "密码不能为空";
                return false;
            }
            //2.定义正则表达式
            var reg_password = /^\w{6,12}$/;
            //3.判断值是否符合正则的规则
            var flag = reg_password.test(newPwd);

            if (flag) {
                tip.innerHTML = "";
            } else {
                //提示密码格式有误
                tip.innerHTML = "密码格式有误";
            }
            return flag;
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;margin-top: 100px">
    <h3 style="text-align: center;">修改密码</h3>
    <form id="form" action="${pageContext.request.contextPath}/userAction.do?command=updatePwd" method="post">
        <div class="form-group">
            <label for="id">用户id：</label>
            <input type="text" class="form-control" id="id" name="id" value="${user.id}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" class="form-control" id="name" name="username" value="${user.username}"
                   readonly="readonly"/>
        </div>

        <div class="form-group">
            <label for="password">原密码：</label>
            <input type="text" class="form-control" id="password" name="money" value="${user.password}"
                   readonly="readonly"/>
        </div>

        <div class="form-group">
            <label for="newPwd">新密码：</label>
            <input type="text" class="form-control" id="newPwd" name="newPwd" placeholder="请输入新密码"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"
                   onclick="window.location='${pageContext.request.contextPath}/userIndex.jsp'"/>
        </div>
    </form>

    <div class="alert" role="alert">
        <span id="tip"></span>
    </div>

</div>
</body>
</html>
