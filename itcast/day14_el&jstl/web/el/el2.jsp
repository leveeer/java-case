<%@ page import="com.jxau.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取对象数据</title>
</head>
<body>
        <%
            User user = new User();
            user.setName("张三");
            user.setAge(23);
            user.setBirthday(new Date());
            request.setAttribute("u",user);

            ArrayList list = new ArrayList();
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");
            list.add(user);
            request.setAttribute("list",list);

            Map map = new HashMap();
            map.put("sname","李四");
            map.put("gender","男");
            map.put("user",user);
            request.setAttribute("map",map);

        %>
        <h3>el获取对象中的值</h3>
        ${requestScope.u}<br>
        <%--通过的是对象的属性来获取--%>
        ${requestScope.u.name}<br>
        ${u.age}<br>
        ${u.birthday.month}<br>
        ${u.birStr}<br>

        <h3>el获取List集合中的值</h3>
        ${list}<br>
        ${list[0]}<br>
        ${list[1]}<br>
        ${list[2]}<br>
        ${list[10]}<br>
        ${list[3].name}<br>

        <h3>el获取map集合中的值</h3>
        ${map.gender}<br>
        ${map["gender"]}<br>
        ${map.user.name}<br>



</body>
</html>
