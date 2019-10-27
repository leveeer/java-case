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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据

        String role = request.getParameter("role");
        System.out.println(role);


        if(!"2".equals(role)){
            //返回登录页面
            request.setAttribute("login_msg","您不是管理员，请选择调研用户登录！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        //封装Admin对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service查询
        AdminService service = new AdminServiceImpl();
        Admin loginAdmin = service.login(admin);

        //判断是否登录成功
        if(loginAdmin != null){
            //登录成功
            //将管理员信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("admin",loginAdmin);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            //登录失败
            //提示信息
            request.setAttribute("login_msg","管理员名字或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
