<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/20
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用银行系统</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        #img{
            margin-top: 50px;
        }

        .title{
            /*margin-left: 50px;*/
            font-family: 华文行楷;
            font-size: 40px;
            text-shadow: 2px 2px 2px black, 2px 2px 2px black;
            color: blue;
        }
        .opera{
            font-family: "Microsoft JhengHei UI";
            /*margin-left: 30px;*/
            font-size: 22px;
            text-shadow: 2px 2px 2px black, 2px 2px 2px black;
            color: blue;
        }

        div {
            margin:0 auto;
            width:100%;
            text-align: center;
        }

        .nav{
            display: table;
            margin: 0 auto;
        }
        .nav li{
            display: table-cell;
        }
        .nav li{
            margin-top: 20px;
            padding-left: 50px;
        }
    </style>
    <script>
        function deposit(isLocked) {
            if (isLocked == 1){
                alert("您的用户已被锁定！请联系管理员进行解锁");
            } else {
                location.href="${pageContext.request.contextPath}/deposit.jsp";
            }
        }

        function withdraw(isLocked) {
            if (isLocked == 1){
                alert("您的用户已被锁定！请联系管理员进行解锁");
            } else {
                location.href="${pageContext.request.contextPath}/withdraw.jsp";
            }
        }

        function transfer(isLocked) {
            if (isLocked == 1){
                alert("您的用户已被锁定！请联系管理员进行解锁");
            } else {
                location.href="${pageContext.request.contextPath}/transfer.jsp";
            }
        }

        function logout() {
            if (confirm("您确定要退出吗？")){
                location.href = "${pageContext.request.contextPath}/userAction.do?command=logout";
            }
        }

    </script>
</head>
<body>
<div>
    <p1 class="title">欢迎您，<span style="color: red">${user.username}</span></p1><br>
    <p3 class="opera">请选择服务</p3>
</div>

<ul class="nav nav-pills" style="text-align: center">

    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/userAction.do?command=inquiry&username=${user.username}">查询余额</a></li>
    <li role="presentation" class="active"><a href="javascript:deposit(${user.isLocked});">存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款</a></li>
    <li role="presentation" class="active"><a href="javascript:withdraw(${user.isLocked});">取&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款</a></li>
    <li role="presentation" class="active"><a href="javascript:transfer(${user.isLocked});">转&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账</a></li>
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/userAction.do?command=record&id=${user.id}">查询明细</a></li>
    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/updatePwd.jsp">修改密码</a></li>
    <li role="presentation" class="active"><a href="javascript:logout();">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡</a></li>
</ul>


<img id="img" src="img/welcome_1.jpg" width="100%" height="450">

<script>
    //修改图片src属性
    var number = 1;
    function fun(){
        number ++ ;
        //判断number是否大于3
        if(number > 3){
            number = 1;
        }
        //获取img对象
        var img = document.getElementById("img");
        img.src = "img/welcome_"+number+".jpg";
    }
    //2.定义定时器
    setInterval(fun,3000);

</script>
</body>
</html>
