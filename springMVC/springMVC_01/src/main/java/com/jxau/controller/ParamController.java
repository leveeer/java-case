package com.jxau.controller;

import com.jxau.domain.Account;
import com.jxau.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/param")
public class ParamController {

    /**
     * 封装超链接参数
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(path = "/testParam")
    public String testParam(String username,String password){
        System.out.println("请求参数方法执行了");
        System.out.println(username);
        System.out.println(password);
        return "success";
    }


    /***
     * 封装表单数据（实体对象）
     * @param account
     * @return
     */
    @RequestMapping(path = "/saveAccount")
    public String saveAccount(Account account) {
        System.out.println(account);
        return "success";
    }

    /**
     * 自定义类型转换器
     * @param user
     * @return
     */
    @RequestMapping(path = "/saveUser")
    public String saveUser(User user) {
        System.out.println(user);
        return "success";
    }

    /**
     * 获取servlet原生API
     * @return
     */
    @RequestMapping(path = "/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        HttpSession session = request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        return "success";
    }

}
