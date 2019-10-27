<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        .button{
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id) {
            //用户安全提示
            if(confirm("您确定要删除吗?")){
                //确定,执行删除操作
                location.href="${pageContext.request.contextPath}/deleteUserServlet?id="+id;

            }
        }
        window.onload = function () {
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                if(confirm("您确定要删除选中条目吗?")){
                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length ; i++) {
                        if(cbs[i].checked){
                            //有至少一个被选中
                            flag = true;
                            break;
                        }
                    }
                    if(flag){//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }
            }

            //获取第一个cb
            document.getElementById("firstCb").onclick = function () {
                //获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //遍历
                for (var i = 0; i < cbs.length ; i++) {
                    //设置这些cbs[i]的状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }

        }

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户注册信息管理</h3>
    <div style="float: right">
        <form class="form-inline" action="" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名:</label>
                <input type="text" name="name" value="" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group" >
                <label for="exampleInputName3">年龄:</label>
                <input type="text" name="address" value="" class="form-control" id="exampleInputName3" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱:</label>
                <input type="text" name="email" value="" class="form-control" id="exampleInputEmail2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: left;margin: 5px" >
        <a class="btn btn-primary" href="javascript:void (0);" id="delSelected">删除选中</a>

    </div>
    <form id="form" action="${pageContext.request.contextPath}/deleteUserServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${users}" var="user" varStatus="s">
                <c:if test="${s.count % 2 != 0}">
                    <tr bgcolor="#faebd7">
                        <td><input type="checkbox" name="uid" value="${user.id}"></td>
                        <td>${s.count}</td>
                        <td>${user.username}</td>
                        <td>${user.gender}</td>
                        <td>${user.age}</td>
                        <td>${user.tel}</td>
                        <td>${user.email}</td>
                        <td><a href="javascript:deleteUser(${user.id}); ">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>
                        </td>
                    </tr>

                </c:if>
                <c:if test="${s.count % 2 == 0}">
                    <tr bgcolor="#00ffff">
                        <td><input type="checkbox" name="uid" value="${user.id}"></td>
                        <td>${s.count}</td>
                        <td>${user.username}</td>
                        <td>${user.gender}</td>
                        <td>${user.age}</td>
                        <td>${user.tel}</td>
                        <td>${user.email}</td>
                        <td><a href="javascript:deleteUser(${user.id}); ">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>
                        </td>
                    </tr>

                </c:if>

            </c:forEach>

        </table>
    </form>
</div>
<div class="button">
    <input type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">
</div>
</body>
</html>