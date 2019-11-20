package com.jxau.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  案例：记住上一次访问时间
 * 		1. 需求：
 * 			1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
 * 			2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串
 *
 * 		2. 分析：
 * 			1. 可以采用Cookie来完成
 * 			2. 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 * 				1. 有：不是第一次访问
 * 					1. 响应数据：欢迎回来，您上次访问时间为:xxxx年xx月xx日xx:xx:xx
 * 					2. 写回Cookie：lastTime=xxxx年xx月xx日xx:xx:xx
 * 				2. 没有：是第一次访问
 * 					1. 响应数据：您好，欢迎您首次访问
 * 					2. 写回Cookie：lastTime=xxxx年xx月xx日xx:xx:xx
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");
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
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为:"+value+"</h1>");
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

            //响应数据
            response.getWriter().write("<h1>您好,欢迎您首次访问</h1>");

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
