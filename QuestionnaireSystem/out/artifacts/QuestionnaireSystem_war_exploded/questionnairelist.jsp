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

</head>
<body>
<div class="container">
    <h3 style="text-align: center">所有问卷信息</h3>
    <div style="float: left">
        <form class="form-inline" action="" method="post">
            <div class="form-group">
                <label for="exampleInputName2">课题:</label>
                <input type="text" name="name" value="" class="form-control" id="exampleInputName2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <form id="form" action="" method="post">
        <!-- 隐藏域提交id -->
        <input type="hidden" name="id" value="${questionnaire.id}">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>课题</th>
                <th>创建时间</th>
                <th>创建人</th>
                <th>截止时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${isUseQuestionnaire}" var="questionnaire" varStatus="s">
                    <tr bgcolor="#faebd7">
                        <td>${s.count}</td>
                        <td>${questionnaire.title}</td>
                        <td>${questionnaire.createtime}</td>
                        <td>${questionnaire.editor}</td>
                        <td>${questionnaire.endtime}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/findQuestionServlet?id=${questionnaire.id}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
            </c:forEach>

        </table>
    </form>
</div>
<div class="button">
    <input type="button" value="返回" onclick="window.location='${pageContext.request.contextPath}/home.jsp'">
</div>
</body>
</html>
