<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/29
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>银行系统首页</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap_admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_adminLeft.css">
    <link href="${pageContext.request.contextPath}/css/style_adminCenter.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery_admin.js"></script>

</head>
<script>
    function deposit(isLocked) {
        if (isLocked == 1) {
            alert("您的用户已被锁定！请联系管理员进行解锁");
        } else {
            location.href = "../pages/deposit.jsp";
        }
    }

    function withdraw(isLocked) {
        if (isLocked == 1) {
            alert("您的用户已被锁定！请联系管理员进行解锁");
        } else {
            location.href = "../pages/withdraw.jsp";
        }
    }

    function transfer(isLocked) {
        if (isLocked == 1) {
            alert("您的用户已被锁定！请联系管理员进行解锁");
        } else {
            location.href = "../pages/transfer.jsp";
        }
    }

    function logout() {
        if (confirm("您确定要退出吗？")) {
            location.href = "${pageContext.request.contextPath}/user/logout.do";
        }
    }
</script>
<body>

<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand">
                <a href="javascript:void(0);">
                    欢迎您，<span style="color: red">${sessionScope.user.username}</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/inquiry.do?id=${sessionScope.user.id}"><i
                        class="fa fa-fw fa-folder"></i>查询余额</a>
            </li>
            <li>
                <a href="javascript:deposit(${sessionScope.user.isLocked});"><i class="fa fa-fw fa-plus"></i>存款</a>
            </li>
            <li>
                <a href="javascript:withdraw(${sessionScope.user.isLocked});"><i class="fa fa-fw fa-bank"></i>取款</a>
            </li>
            <li>
                <a href="javascript:transfer(${sessionScope.user.isLocked});"><i class="fa fa-fw fa-twitter"></i>转账</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/record.do?id=${sessionScope.user.id}"><i
                        class="fa fa-fw fa-file-o"></i>查询明细</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/pages/updatePwd.jsp"><i class="fa fa-fw fa-cog"></i>修改密码</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/logout.do"><i class="fa fa-fw fa-home"></i>退出系统</a>
            </li>
        </ul>
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">

            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <p style="margin-top: -50px;margin-left: 65px; font-size: 24px">菜单栏</p>
        <div class="Choices">
            <div class="Choices_text">
                <div class="Choices_head">银行系统</div>
                <div class="Choices_content">
                    <div class="Choicesnext"><img src="${pageContext.request.contextPath}/img/Choicesnext.png"
                                                  width="38"></div>
                    <div class="Choices_banner">
                        <ul>
                            <li>
                                <div class="Choices_left"><img src="${pageContext.request.contextPath}/img/Choices1.jpg"
                                                               width="594"></div>
                                <div class="Choices_right">
                                    <h1>闲钱也能增值</h1>
                                    <h2></h2>
                                    <p>钱不能“闲”，短打理促收益，“巧”能生财，提升技能投资有道</p>
                                </div>
                            </li>
                            <li>
                                <div class="Choices_left"><img src="${pageContext.request.contextPath}/img/Choices2.jpg"
                                                               width="594"></div>
                                <div class="Choices_right">
                                    <h1>专业理财顾问</h1>
                                    <h2></h2>
                                    <p>速盈"T + 1"模式，更加专业的理财团队，让您快速从中受益。</p>
                                </div>
                            </li>
                            <li>
                                <div class="Choices_left"><img src="${pageContext.request.contextPath}/img/Choices3.jpg"
                                                               width="594"></div>
                                <div class="Choices_right">
                                    <h1>个人大额存单</h1>
                                    <h2></h2>
                                    <p>收益率高，流动性好，安全性强，功能丰富。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="Choicestel">
                        <ul>
                            <li><01/03></li>
                            <li><02/03></li>
                            <li><03/03></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap_admin.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Choices.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        var trigger = $('.hamburger'),
            overlay = $('.overlay'),
            isClosed = false;

        trigger.click(function () {
            hamburger_cross();
        });

        function hamburger_cross() {

            if (isClosed === true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }

        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>

</body>
</html>