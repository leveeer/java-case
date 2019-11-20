<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/1
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style>
        *{
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }
        body{
            background: url("${pageContext.request.contextPath}/img/center.jpg") no-repeat center;
            width: 100%;
        }
        .rg_layout{
            width: 900px;
            height: 500px;
            border: 8px solid #EEEEEE;
            background-color: white;
            margin: auto;
            margin-top: 100px;
        }

        .rg_top{
            /*border: 1px solid red;*/
            margin-top: 80px;
            margin-left: 310px;
        }
        .rg_top>p:first-child{
            font-size: 40px;
            margin: 15px;
            text-shadow: 5px 5px 5px black, 0px 0px 2px black;
            color: #FFD026;
        }
        .rg_center{
            /*border: 1px solid red;*/
            margin-left: 250px;
            margin-top: 30px;
            width: 450px;
        }
        .td_left{
            width: 100px;
            text-align: right;
            height: 45px;
        }
        .td_right{
            padding-left:50px ;
        }
        #username,#password{
            width: 251px;
            height: 32px;
            border: 1px solid #A6A6A6;
            border-radius: 5px;
            padding-left: 10px;
        }
        #btn_sub1{
            width: 80px;
            height: 30px;
            background-color: #FFD026;
            margin-left: 30px;
            border: 0px solid #FFD026;
        }
        .alert{
            font-size: 20px;
            margin-top: 20px;
            text-align: center;
            display: block;
            color: red;
        }
    </style>
</head>
<body>

<div class="rg_layout">
    <div class="rg_top">
        <p><i>z l s g 聊 天 室</i></p>
    </div>

    <div class="rg_center">
        <div class="rg_form">
            <form name="form" action="${pageContext.request.contextPath}/chatServlet/login"  method="post">
                <table>
                    <tr>
                        <td class="td_left" >用户名：<label for="username"></label></td>
                        <td class="td_right"><input type="text" name="username" id="username" placeholder="请输入用户名"></td>
                    </tr>

                    <tr>
                        <td class="td_left" >密  码：<label for="password"></label></td>
                        <td class="td_right"><input type="password" name="password" id="password" placeholder="请输入密码"></td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 20px;">
                    <input type="submit" class="active" value="登   录" id="btn_sub1">
                </div>
            </form>

            <!-- 用户名或密码错误提示 -->
            <div class="alert" role="alert">
                <span>${login_error}</span>
            </div>
        </div>
    </div>

</div>
</body>

</html>
