<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>foreach标签</title>
</head>
<body>
        <c:forEach begin="1" end="10" var="i" step="2" varStatus="s">
            ${i} ${s.index} ${s.count}<br>
        </c:forEach>

        <hr>

        <%
            List list = new ArrayList();
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");
            request.setAttribute("list",list);
        %>

        <c:forEach items="${list}" var="value" varStatus="s">
            ${s.index} ${s.count} ${value}<br>


        </c:forEach>

</body>
</html>
