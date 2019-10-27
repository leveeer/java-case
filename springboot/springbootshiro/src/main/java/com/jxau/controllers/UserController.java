package com.jxau.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        return "ok";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","江西农业大学");
        return "test";
    }

    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到test.html
            return "redirect:/testThymeleaf";
        }catch (UnknownAccountException e){
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
