package com.jxau.web.servlet;

import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.service.AdminService;
import com.jxau.service.impl.AdminServiceImpl;
import com.jxau.util.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

//@WebServlet("/adminServlet/*")
public class AdminServlet extends BaseServlet {

    private AdminService adminService = new AdminServiceImpl();

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //封装数据
        Map<String, String[]> map = request.getParameterMap();

        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Service
        Admin adminLogin = adminService.login(admin);

        //判断是否登录成功
        if (adminLogin != null){
            //登录成功
            //将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("admin",adminLogin);
            //跳转页面
            response.sendRedirect(request.getContextPath() + "/adminIndex.jsp");
        }else {
            //登录失败
            //将错误信息存入request域
            request.setAttribute("login_error","用户名或密码错误！");
            //转发
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }


    /**
     * 查询都有用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> users = adminService.findAllUser();
        request.setAttribute("users",users);

        request.getRequestDispatcher("/userlist.jsp").forward(request,response);
    }

    /**
     * 修改用户状态
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeLocked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String isLocked = request.getParameter("isLocked");

        adminService.changLocked(id,isLocked);
        response.sendRedirect("adminServlet/findAllUser");
    }

    /**
     * 根据条件查询用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        Map<String, String[]> condiction = request.getParameterMap();
        //调用Service查询
        List<User> users = adminService.findUser(condiction);
        request.setAttribute("users",users);
        request.setAttribute("condiction",condiction);
        //跳转页面
        request.getRequestDispatcher("/userlist.jsp").forward(request,response);
    }


}
