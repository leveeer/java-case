<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jxau</title>
</head>
<body>

<%

    //获取所有的cookie
    Cookie[] cookies = request.getCookies();
    boolean flag = false;//没有cookie为lastTime
    //遍历
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            //获取cookie的名称
            String name = cookie.getName();
            //判断名称是否是:lastTime
            if("lastTime".equals(name)){
                //有该cookie,不是第一次访问
                flag = true;//有lastTime的cookie

                //设置Cookie的Value
                //获取当前时间的字符串,重新设置Cookie的值,重新发送Cookie
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String str_date = sdf.format(date);
                //对str_date编码,重新赋值
                str_date = URLEncoder.encode(str_date, "utf-8");
                //将str_date设置为cookie的值
                cookie.setValue(str_date);

                //设置Cookie的存活时间
                cookie.setMaxAge(60*60*24*30);//一个月
                response.addCookie(cookie);

                //响应数据
                //获取cookie的Value
                String value = cookie.getValue();
                //对cookie的值进行解码,重新赋值
                value = URLDecoder.decode(value,"utf-8");
%>

                <h1>欢迎回来，您上次访问时间为:<%=value%></h1>

<%
                break;
            }
        }
    }

    if(cookies == null || cookies.length == 0 || flag == false){
        //没有lastTime的cookie,即第一次访问
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str_date = sdf.format(date);
        //对str_date编码,重新赋值
        str_date = URLEncoder.encode(str_date, "utf-8");
        Cookie cookie = new Cookie("lastTime",str_date);

        //设置Cookie的存活时间
        cookie.setMaxAge(60*60*24*30);//一个月
        response.addCookie(cookie);
%>
        <h1>您好,欢迎您首次访问</h1>
<%
    }
%>

</body>
</html>
