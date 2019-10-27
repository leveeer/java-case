package com.jxau.web.servlet;

import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.service.impl.UserServiceImpl;
import com.jxau.utils.CommonsUtils;
import com.jxau.utils.MailUtils;
import com.jxau.utils.MyBeanUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author xie
 */
public class RegisterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //封装数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        MyBeanUtils.populate(user,map);
        //封装未填充的数据
        user.setUid(CommonsUtils.getUUID());
        user.setTelephone(null);
        user.setState(0);
        String activeCode = CommonsUtils.getUUID();
        user.setCode(activeCode);
        UserService userService = new UserServiceImpl();
        boolean flag = false;
        try {
            flag = userService.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flag){
            //发送激活邮件
            String emailMsg = "恭喜您注册成功，请点击下面的链接进行激活账户<a href='http://49.235.212.79:8080/shop/active?activeCode=" + activeCode + "' >" + "http://localhost:49.235.212.79/shop/active?activeCode=" + activeCode + "</a>";

            try {
                MailUtils.sendMail(user.getEmail(),emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }


            response.sendRedirect(request.getContextPath() + "/register_success.jsp");
        }else {
            response.sendRedirect(request.getContextPath() + "/register_fail.jsp");
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
