package com.jxau.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sessionDemo03")
public class SessionDemo03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取session共享对象
        HttpSession session = request.getSession();
        //期望客户端关闭后session也能相同
        Cookie c = new Cookie("JSESSIONID", session.getId());
        c.setMaxAge(60*60);
        response.addCookie(c);
        System.out.println(session);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
