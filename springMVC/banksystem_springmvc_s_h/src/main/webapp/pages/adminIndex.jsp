<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/18
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title></title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:200,300,400,600,700">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"><!--演示页面样式，使用时可以不引用-->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo.css">

</head>
<body>

<div id="container">

    <header>

        <div class="wrapper cf">

            <nav id="main-nav">

                <ul class="first-nav">
                    <li class="cryptocurrency">
                        <a href="#" target="_blank">用户信息管理</a>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/admin/findAllUser.do">用户信息</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="second-nav">
                    <li class="devices">
                        <a href="#">银行运营管理</a>
                        <ul>
                            <li><a href="#">收支详情</a></li>
                        </ul>
                    </li>
                    <li class="magazines">
                        <a href="#">银行贷款管理</a>
                        <ul>
                            <li><a href="#">贷款详情</a></li>
                        </ul>
                    </li>
                    <li class="store">
                        <a href="#">理财产品管理</a>
                        <ul>
                            <li>
                                <a href="#">转账</a>
                            </li>
                        </ul>
                    </li>
                    <li class="collections">
                        <a href="#">利息调整</a>
                        <ul>
                            <li>
                                <a href="#">存款利息</a>
                                <a href="#">贷款利息</a>
                            </li>
                        </ul>
                    </li>

                    <li class="credits"><a href="${pageContext.request.contextPath}/admin/logout.do">退出</a></li>
                </ul>

            </nav>

            <h1>银行后台管理系统</h1>
            <h3>欢迎您，管理员<span style="color: red">${sessionScope.admin.username}</span></h3><br>

            <h5 style="font-family: 宋体">适用浏览器：FireFox、Chrome、Opera、傲游、搜狗、世界之窗. 不支持Safari、IE8及以下浏览器。<br></h5>

            <a class="toggle">
                <span></span>
                菜单栏
            </a>

        </div>

    </header>

    <main>

        <div class="wrapper">

            <div class="content">

                <h4>选择导航出现的位置</h4>

                <div class="actions">

                    <div><a href="#" data-demo="{side:'left'}" class="button active">左侧</a></div>

                    <div><a href="#" data-demo="{side:'right'}" class="button">右侧</a></div>

                </div>

                <h4>水平开放</h4>

                <div class="actions">

                    <div><a href="#" data-demo="{levelOpen:'overlap', levelSpacing:40}" class="button active">重叠</a></div>

                    <div><a href="#" data-demo="{levelOpen:'expand', levelSpacing:25}" class="button">扩大</a></div>

                    <div><a href="#" data-demo="{levelOpen:false, levelSpacing:25}" class="button">展开</a></div>

                </div>

            </div>

        </div>

    </main>


</div>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/hc-mobile-nav.js"></script>
<script>

    (function ($) {
        var $nav = $('#main-nav');
        var $toggle = $('.toggle');
        var defaultData = {
            maxWidth: false,
            customToggle: $toggle,
            navTitle: '请选择操作',
            levelTitles: true,
        };

        // we'll store our temp stuff here
        var $clone = null;
        var data = {};

        // calling like this only for demo purposes

        const initNav = function (conf) {
            if ($clone) {
                // clear previous instance
                $clone.remove();
            }

            // remove old toggle click event
            $toggle.off('click');

            // make new copy
            $clone = $nav.clone();

            // remember data
            $.extend(data, conf)

            // call the plugin
            $clone.hcMobileNav($.extend({}, defaultData, data));
        }

        // run first demo
        initNav({});

        $('.actions').find('a').on('click', function (e) {
            e.preventDefault();

            var $this = $(this).addClass('active');
            var $siblings = $this.parent().siblings().children('a').removeClass('active');

            initNav(eval('(' + $this.data('demo') + ')'));
        });
    })(jQuery);
</script>

</body>
</html>
