package com.jxau.xw.web.sevlet;

import com.jxau.xw.domain.User;
import com.jxau.xw.service.UserService;
import com.jxau.xw.service.impl.UserServiceImpl;
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

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Service查询
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);

        //判断是否登录成功
        if(loginUser != null){
            //登录成功
            //将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/home.jsp");
        }else {
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }
}
