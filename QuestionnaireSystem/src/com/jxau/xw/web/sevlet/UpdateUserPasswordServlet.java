package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.User;
import com.jxau.xw.service.UserService;
import com.jxau.xw.service.impl.UserServiceImpl;
import com.jxau.xw.utils.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/updateUserPasswordServlet")
public class UpdateUserPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装数据
        User user = new User();
        MyBeanUtils.populate(user,map);

        UserService service = new UserServiceImpl();
        service.updateUserPassword(user);

        //跳转回登录页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
