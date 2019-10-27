<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/20
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>自动跳转</title>
    <style>
        div{
            text-align: center;
        }
        span{
            color:red;
        }
        body{
            background-color: #5bc0de;
        }

    </style>

</head>
<body>
<div>
    <h1>
        注册成功,<span id="time">3</span>秒之后，自动跳转到登录页面
    </h1>
</div>

<script>
    var second = 3;
    var time = document.getElementById("time");
    //定义一个方法，获取span标签，修改span标签体内容，时间--
    function showTime(){
        second -- ;
        //判断时间如果<= 0 ，则跳转到首页
        if(second <= 0){
            //跳转到首页
            location.href = "${pageContext.request.contextPath}/login.jsp";
        }
        time.innerHTML = second +"";
    }
    //设置定时器，1秒执行一次该方法
    setInterval(showTime,1000);
</script>
</body>
</html>
    