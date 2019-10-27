
<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/29
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

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
            float: right;
            margin-right: 200px;
            margin-top: 20px;
        }
        body{
            background-color: #426785;
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
                    location.href="${pageContext.request.contextPath}/admin/changeLocked.do?id=" + id + "&isLocked=" + isLocked;
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
                    location.href="${pageContext.request.contextPath}/admin/changeLocked.do?id=" + id +"&isLocked=" + isLocked;
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
    <h2 style="text-align: center; color: white">用户管理</h2>
    <div style="float: right">
        <form class="form-inline" action="${pageContext.request.contextPath}/admin/findUserByCondition.do" method="post">
            <%--<div class="form-group">
                <label for="exampleInputName2">用户id:</label>
                <input type="text" name="id" value="" class="form-control" id="exampleInputName2" >
            </div>--%>
            <div class="form-group" >
                <label for="exampleInputName3">用户名:</label>
                <input type="text" name="username" value="${username}" class="form-control" id="exampleInputName3" >
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

            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
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

<div>
<!--分页 -->
    <div style="width: 380px; float: left;margin-left: 200px; margin-top: 10px">
        <ul class="pagination" style="text-align: center; margin-top: 10px;">
            <%--上一页--%>
            <c:if test="${pageBean.currentPage == 1}">
                <li class="disabled">
                    <a href="javascript:void (0)" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage != 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/findAllUser.do?currentPageStr=${pageBean.currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <%--显示每一页--%>
            <c:forEach begin="1" end="${pageBean.totalPage}" var="page">
                <%--判断是否是当前页--%>
                <c:if test="${page == pageBean.currentPage}">
                    <li class="active"><a href="javascript:void(0);">${page}</a></li>
                </c:if>
                <c:if test="${page != pageBean.currentPage}">
                    <li><a href="${pageContext.request.contextPath}/admin/findAllUser.do?currentPageStr=${page}">${page}</a></li>
                </c:if>
            </c:forEach>
            <%--下一页--%>

            <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                <li class="disabled">
                    <a href="javascript:void (0)" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/findAllUser.do?currentPageStr=${pageBean.currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
<!-- 分页结束 -->

    <div class="button">
        <input type="button" class="btn btn-primary" value="显示全部用户" onclick="window.location='${pageContext.request.contextPath}/admin/findAllUser.do'">
        <input type="button" class="btn btn-primary" value="返回" onclick="window.location='/pages/adminIndex.jsp'">
    </div>
</div>
</body>
</html>