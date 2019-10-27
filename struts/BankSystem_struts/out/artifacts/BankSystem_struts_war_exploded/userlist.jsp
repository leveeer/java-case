<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/29
  Time: 14:52
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
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户管理</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        .button{
            text-align: center;
        }
    </style>
    <script>
        function unLocked(id,isLocked) {
            if (isLocked == 0){
                alert("该用户未被冻结！");
            }else if (isLocked == 1) {
                //用户安全提示
                if(confirm("您确定为该用户解冻吗?")){
                    //确定,执行解冻操作
                    isLocked = 0;
                    location.href="${pageContext.request.contextPath}/adminAction.do?command=changeLocked&id=" + id + "&isLocked=" + isLocked;
                }
            }
        }

        function locked(id,isLocked) {
            if (isLocked == 1){
                alert("该用户已被冻结！");
            }else if (isLocked == 0) {
                //用户安全提示
                if(confirm("您确定要冻结该用户吗?")){
                    //确定,执行冻结操作
                    isLocked = 1;
                    location.href="${pageContext.request.contextPath}/adminAction.do?command=changeLocked&id=" + id +"&isLocked=" + isLocked;
                }
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
                var cbs = document.getElementsByName("id");
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
    <h2 style="text-align: center">用户管理</h2>
    <div style="float: right">
        <form class="form-inline" action="${pageContext.request.contextPath}/adminAction.do?command=findUserByCondition" method="post">
            <div class="form-group">
                <label for="exampleInputName2">用户id:</label>
                <input type="text" name="id" value="${condition.id}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group" >
                <label for="exampleInputName3">用户名:</label>
                <input type="text" name="username" value="${condition.username}" class="form-control" id="exampleInputName3" >
            </div>
            <%--<div class="form-group">
                <label for="exampleInputEmail2">#</label>
                <input type="text" name="email" value="" class="form-control" id="exampleInputEmail2" >
            </div>--%>
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
    </div>

    <div style="float: left;margin: 5px" >
        <a class="btn btn-primary" href="javascript:void (0);" id="delSelected">删除选中</a>

    </div>
    <form id="form" action="#" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>用户id</th>
                <th>用户名</th>
                <th>余额</th>
                <th>是否锁定</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${users}" var="user" varStatus="s">
                    <tr bgcolor="#faebd7">
                        <td><input type="checkbox" name="id" value="${user.id}"></td>
                        <td>${s.count}</td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.money}</td>
                        <td>${user.isLocked}</td>
                        <td>
                            <a class="btn btn-default btn-sm" href="javascript:unLocked('${user.id}','${user.isLocked}'); ">解冻</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="btn btn-default btn-sm" href="javascript:locked('${user.id}','${user.isLocked}'); ">冻结</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <%--<a href="javascript:deleteUser(${user.id}); ">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>--%>
                        </td>
                    </tr>
            </c:forEach>

        </table>
    </form>
</div>
<div class="button">
    <input type="button" class="btn btn-primary" value="显示全部用户" onclick="window.location='${pageContext.request.contextPath}/adminAction.do?command=findAllUser'">
    <input type="button" class="btn btn-primary" value="返回" onclick="window.location='${pageContext.request.contextPath}/adminIndex.jsp'">
</div>
</body>
</html>