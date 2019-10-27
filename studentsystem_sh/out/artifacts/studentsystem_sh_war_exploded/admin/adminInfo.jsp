<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/8/21
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@  page  isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />

    <style type="text/css">
        body {
            background:url(/images/bg.gif);
        }
    </style>
</head>

<body leftmargin="2" topmargin="2" >
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
    <tr bgcolor="#E7E7E7">
        <td height="14" colspan="6" align='center' >&nbsp;管理员维护&nbsp;</td>
    </tr>
    <tr align="center" bgcolor="#FAFAF1" height="22">
        <td width="50%">ID</td>
        <td width="50%">管理员</td>
    </tr>
    <c:forEach items="${admins}" var="admin">
        <tr align='center' bgcolor="#FFFFFF" height="22">
            <td bgcolor="#FFFFFF" align="center">
                    ${admin.id}
            </td>
            <td bgcolor="#FFFFFF" align="center">
                    ${admin.name}
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>

