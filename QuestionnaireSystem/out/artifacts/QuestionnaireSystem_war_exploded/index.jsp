<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <style>
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }

        body{
            background: url("img/timg1.jpg") no-repeat center ;
        }
        .index_layout{
            width: 1000px;
            height: 800px;
            /*border: 8px solid #EEEEEE;
            background-color: white;*/
            margin: auto;
            margin-top: 15px;
        }
        .index_top>p:first-child{
            margin-top: 100px;
            text-align: center;
            font-size: 30px;
            color: gray;
        }
        .index_top>p:last-child{
            float: right;
            margin-right: 100px;
            font-size: 12px;
            color: gray;
        }
        .index_center{
            float: right;
            margin-top: 100px;
            margin-right: 100px;
            color: cyan;
        }
        ul {

            float: left;
            list-style-type:none;
            margin-top: 30px;
            margin-left: 20px;

        }
        a:link,a:visited {
            background-color: darkgray;
            display:block;
            font-weight:bold;
            color:#FFFFFF;
            /*background-color:#bebebe;*/
            width:120px;
            height: 30px;
            text-align:center;
            padding:4px;
            text-decoration:none;
            text-transform:uppercase;
        }
        a:hover,a:active {
            background-color:#cc0000;
        }
    </style>
</head>
<body>
<div class="index_layout">
    <div class="index_top">
        <p>问卷调研管理系统</p>
        <p>欢迎您：${admin.username}</p>
    </div>

    <div class="index_center">
        <h3>欢迎${admin.username}使用本系统，请选择左边的菜单栏进行操作</h3>
    </div>

    <ul>
        <li><a href="${pageContext.request.contextPath}/questionnaireListServlet">调查课题管理</a></li>
        <li><a href="${pageContext.request.contextPath}/addquestionnaire.jsp">添加新课题</a></li>
        <li><a href="${pageContext.request.contextPath}/adminFindAllMessageServlet">留言信息管理</a></li>
        <li><a href="${pageContext.request.contextPath}/userListServlet">注册用户管理</a></li>
        <li><a href="${pageContext.request.contextPath}/surveyrecordsselect.jsp">调查记录查询</a></li>
        <li><a href="${pageContext.request.contextPath}/findAdminServlet?id=${admin.id}">修改个人密码</a></li>
        <li><a href="${pageContext.request.contextPath}/adminLogoutServlet">退出登录</a></li>
    </ul>
</div>
</body>
</html>

