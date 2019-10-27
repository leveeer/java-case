package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.Admin;
import com.jxau.xw.service.AdminService;
import com.jxau.xw.service.impl.AdminServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateAdminPasswordServlet")
public class UpdateAdminPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String newpassword = request.getParameter("newpassword");
        System.out.println(newpassword);

        //调用Service修改
        AdminService service = new AdminServiceImpl();
        service.updateAdminPassword(newpassword);
        //跳转回登录界面
        response.sendRedirect(request.getContextPath()+"/login.jsp");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
