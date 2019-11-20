<%--
  Created by IntelliJ IDEA.
  User: xie
  Date: 2019/7/22
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String checkboxes = request.getParameter("checkbox");

    //System.out.println(checkboxes);


    if (checkboxes != null) {
        //用户选择了记住密码
        if ("zs".equals(username) && "123".equals(password)) {
            //创建一个新的Cookie
            Cookie cookie = new Cookie("zs", "123");
            cookie.setMaxAge(20);
            //发送Cookie到客户端
            response.addCookie(cookie);
            //跳转到成功页面
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        } else {
            //用户名或密码错误,跳转到失败页面
            response.sendRedirect("fail.jsp");
        }
    } else {
        //用户没有选择记住密码
        if ("zs".equals(username) && "123".equals(password)) {
            //跳转到成功页面
            request.getRequestDispatcher("/success.jsp").forward(request, response);


        } else {
            //用户名或密码错误,跳转到失败页面
            response.sendRedirect("fail.jsp");
        }


    }

%>

</body>
</html>
