<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  ${3>4}
  \${3>4}<%--忽略el表达式 --%>
  ${5 div 1}
  ${2 + 3}
  ${4 * 5}
  ${3 % 4}
  ${3 mod 4}
  ${20 / 5}
  <h4>empty运算符</h4>
  <%
    String str = null;
    request.setAttribute("str",str);

    List list = new ArrayList();
    request.setAttribute("list",list);
  %>
  ${not empty str}<br>
  ${not empty list}<br>
  </body>
</html>
