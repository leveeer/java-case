<%--
  Created by IntelliJ IDEA.
  User: S1
  Date: 2019/9/13
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请求参数绑定测试</title>
</head>
<body>
            <%--<a href="param/testParam?username=zs&password=123">请求参数绑定</a>--%>

            <%--<form action="param/saveAccount" method="post">
                用户名：<input type="text" name="username"><br>
                密 码：<input type="password" name="password"><br>
                金 额：<input type="text" name="money"><br>

                姓 名：<input type="text" name="user.uname"><br>
                年 龄：<input type="text" name="user.age"><br>
                <input type="submit" value="提交">
            </form>--%>

            <%--&lt;%&ndash;把数据封装到Account类中，该类存在list集合和map集合&ndash;%&gt;
            <form action="param/saveAccount" method="post">
                用户名：<input type="text" name="username"><br>
                密 码：<input type="password" name="password"><br>
                金 额：<input type="text" name="money"><br>

                姓 名：<input type="text" name="list[0].uname"><br>
                年 龄：<input type="text" name="list[0].age"><br>

                姓 名：<input type="text" name="map['1'].uname"><br>
                年 龄：<input type="text" name="map['1'].age"><br>
                <input type="submit" value="提交">
            </form>--%>


            <%--&lt;%&ndash;自定义类型转换器&ndash;%&gt;
            <form action="param/saveUser" method="post">

                姓 名：<input type="text" name="uname"><br>
                年 龄：<input type="text" name="age"><br>
                生 日：<input type="text" name="birthday"><br>
                <input type="submit" value="提交">
            </form>--%>

            <a href="param/testServlet" value="">获取servlet原生api</a>

</body>
</html>
