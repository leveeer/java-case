<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <style type="text/css">
        div {
            padding: 0px;
            margin: 0px;
        }

        body {
            scrollbar-base-color: #bae87c;
            scrollbar-arrow-color: #FFFFFF;
            scrollbar-shadow-color: #c1ea8b;
            padding: 0px;
            margin: auto;
            text-align: center;
            background-color: #9ad075;
        }

        dl.bitem {
            width: 148px;
            margin: 0px 0px 5px 4px;
        }

        dl.bitem dt {
            background: url(<%=path %>/images/hide.gif);
            height: 26px;
            line-height: 26px;
            text-align: center;
            cursor: pointer;
        }

        dl.bitem dd {
            padding: 3px 3px 3px 3px;
            background-color: #fff;
        }

        .fllct {
            float: left;

            width: 90px;
        }

        .flrct {
            padding-top: 3px;
            float: left;
        }

        div.items {
            line-height: 22px;
            background: url(<%=path %>/images/arr.gif) no-repeat 10px 9px;
        }

        span.items {
            padding: 10px 0px 10px 22px;
            background: url(<%=path %>/images/arr.gif) no-repeat 10px 12px;
        }

        ul {
            padding-top: 3px;
        }

        li {
            height: 22px;
        }

        .sitemu li {
            padding: 0px 0px 0px 22px;
            line-height: 24px;
            background: url(<%=path %>/images/arr.gif) no-repeat 10px 9px;
        }
    </style>
    <script language='javascript'>var curopenItem = '1';</script>
    <script language="javascript" type="text/javascript" src="<%=path %>/js/menu.js"></script>
    <base target="main"/>
    <script type="text/javascript">
        $(function () {
            //alert(${student.id});
        });
    </script>
</head>

<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
    <tr>
        <td style='padding-left:3px;padding-top:8px' valign="top">
            <dl class='bitem'>
                <dt onClick='showHide("items1_1")'><b>学生信息</b></dt>
                <dd style='display:block' class='sitem' id='items1_1'>
                    <ul class='sitemu'>
                        <li><a href="<%=path %>/student/SeestudentInfo.jsp" target='main'>学生信息查询</a></li>
                    </ul>
                </dd>
            </dl>
            <dl class='bitem'>
                <dt onClick='showHide("items2_1")'><b>学生成绩</b></dt>
                <dd style='display:block' class='sitem' id='items2_1'>
                    <ul class='sitemu'>
                        <li><a href="<%=path %>/studentServlet/scoreInquiry?id=${student.id}" target='main'>学生成绩查询</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl class='bitem'>
                <dt onClick='showHide("items3_1")'><b>通告</b></dt>
                <dd style='display:block' class='sitem' id='items3_1'>
                    <ul class='sitemu'>
                        <li><a href='<%=path %>/studentServlet/announceInquiry' target='main'>通告信息查看</a></li>
                    </ul>
                </dd>
            </dl>
            <dl class='bitem'>
                <dt onClick='showHide("items4_1")'><b>修改个人密码</b></dt>
                <dd style='display:block' class='sitem' id='items4_1'>
                    <ul class='sitemu'>
                        <li><a href="<%=path %>/student/revisepwd.jsp" target='main'>修改个人密码</a></li>
                    </ul>
                </dd>
            </dl>
            <dl class='bitem'>
                <dt onClick='showHide("items99_1")'><b>退出登录</b></dt>
                <dd style='display:block' class='sitem' id='items99_1'>
                    <ul class='sitemu'>
                        <li><a href='<%=path %>' onclick='javascript:window.parent.location="<%=path %>/studentServlet/logout"'>退出登录</a></li>
                    </ul>
                </dd>
            </dl>
        </td>
    </tr>
</table>
</body>
</html>
