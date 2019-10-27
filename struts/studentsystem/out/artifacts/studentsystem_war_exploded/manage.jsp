<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>欢迎登陆</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            scrollbar-base-color: #ebf7f5;
            scrollbar-arrow-color: #FFFFFF;
        }
    </style>
</head>
<frameset rows="110,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=path %>/top.jsp" name="topFrame" scrolling="no">
    <frameset cols="180,*" name="btFrame" frameborder="NO" border="0" framespacing="0">
        <frame src="<%=path %>/admenu.jsp" noresize name="menu" scrolling="yes">
        <frame src="<%=path %>/welcome.jsp" noresize name="main" scrolling="yes">
    </frameset>
</frameset>
<noframes>
    <body>您的浏览器不支持框架！</body>
</noframes>
</html>