<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language='javascript'>
        function logout() {
            if (confirm("确定要退出本系统吗??")) {
                window.parent.location = "<%=path %>/login.jsp";
            }
        }
        function reflash(){

            window.parent.location="Reflash";

        }
    </script>
    <style type="text/css">
        body {
            background-image: url(${pageContext.request.contextPath}/images/header.gif);
        }
    </style>
</head>

<body bgColor='#9ad075' style="margin: 0;padding: 0">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width='100%' height="70" style="text-align:center; font-size:34px; font-weight: bold;">&nbsp;&nbsp;学生信息管理系统欢迎您</td>
    </tr>
    <tr>
        <td width='100%' align="right">
            <div class="rigth">
                <a href="#" onclick="logout()">[退出系统]</a> &nbsp;&nbsp;<a href="#" onclick="reflash()">[刷新]</a>
            </div>
        </td>
    </tr>
</table>
</body>
</html>