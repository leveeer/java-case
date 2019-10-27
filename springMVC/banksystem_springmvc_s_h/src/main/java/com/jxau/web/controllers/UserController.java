package com.jxau.web.controllers;


import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;


/**
 * @ClassName UserAction
 * @Description TODO
 * @Author xie
 * @Date 2019/8/19 14:08
 * @Version 1.0
 */
@Controller
@RequestMapping(path = "/user")
public class UserController{

    @Resource(name = "userService")
    private UserService userService;

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //注册
     * @Date 2019/8/19 14:17
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("register.do")
    public String register(User user){
        System.out.println(user);
        user.setMoney(new BigDecimal("0.00"));
        userService.userRegister(user);
        return "register_success";
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //登录
     * @Date 2019/8/19 14:19
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("login.do")
    public String login(User user, HttpServletRequest request){
        User userLogin = userService.userLogin(user);
        // 判断是否登录成功
        if (userLogin != null) {
            // 登录成功
            // 将用户信息存入session
            HttpSession session = request.getSession();
            session.setAttribute("user", userLogin);
            // 跳转页面
            return "userIndex";
        } else {
            // 登录失败
            // 将错误信息存入request域
            request.setAttribute("login_error", "用户名或密码错误！");
            System.out.println(request.getAttribute("login_error"));
            // 转发
            return "forward:/login.jsp";
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //查询余额
     * @Date 2019/8/19 14:23
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("inquiry.do")
    public String inquiry(String id,HttpServletRequest request){
        User user = userService.findUserByUid(id);
        request.setAttribute("user", user);
        return "inquiry";
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //存款
     * @Date 2019/8/19 14:25
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("deposit.do")
    public String deposit(User user, HttpServletRequest request){

        int id = user.getId();
        BigDecimal money = user.getMoney();

        userService.deposit(money, id);
        request.setAttribute("opera_success", "存款成功");

        return "opera_success";
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //取款
     * @Date 2019/8/19 14:27
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("withdraw.do")
    public String withdraw(User user, HttpServletRequest request){

        int id = user.getId();
        BigDecimal money = user.getMoney();
        boolean flag = userService.withdraw(id, money);
        if (flag) {
            request.setAttribute("opera_success", "取款成功");
            return "opera_success";
        } else {
            request.setAttribute("withdraw_error", "您的余额不足！");
            return "withdraw";
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //转账
     * @Date 2019/8/19 14:30
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("transfer.do")
    public String transfer(User user, HttpServletRequest request){

        int id = user.getId();
        BigDecimal money = user.getMoney();
        String getMoneyUser = user.getGetMoneyUser();

        if (userService.findUserByUsername(getMoneyUser) == null) {
            request.setAttribute("transfer_error", "您输入的用户不存在！");
            return "transfer";
        } else {
            boolean flag = userService.transfer(id, getMoneyUser, money);

            if (flag) {
                request.setAttribute("opera_success", "转账成功");
                return "opera_success";
            } else {
                request.setAttribute("transfer_error", "您的余额不足");
                return "transfer";
            }
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //查询明细
     * @Date 2019/8/19 14:32
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("record.do")
    public String record(String id, String currentPageStr,HttpServletRequest request){

        if (currentPageStr == null) {
            currentPageStr = "1";
        }
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 5;
        PageBean pageBean = userService.findRecordByPage(id, currentPage, currentCount);

        request.setAttribute("pageBean", pageBean);

        return  "record";
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //修改密码
     * @Date 2019/8/19 14:34
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("updatePwd.do")
    public String updatePwd(User user, HttpServletRequest request){

        int id = user.getId();
        String newPwd = user.getNewPwd();
        userService.updatePwdById(id, newPwd);
        return "redirect:/login.jsp";
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //退出
     * @Date 2019/8/19 14:35
     * @Param [mapping, form, request, response]
     **/
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request){
        User userLogin = (User) request.getSession().getAttribute("userLogin");

        if (userLogin == null) {
            return "redirect:/login.jsp";
        } else {
            request.getSession().invalidate();

            return "redirect:/login.jsp";
        }
    }

}
