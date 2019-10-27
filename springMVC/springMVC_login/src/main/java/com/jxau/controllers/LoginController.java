package com.jxau.controllers;

import com.jxau.domain.User;
import com.jxau.exceptions.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login.do")
    public String login(User user){
        if (!"admin".equals(user.getUsername())){
            throw new SysException("用户未找到");
        }else if(!"admin".equals(user.getPassword())){
            throw new SysException("密码错误");
        }else {
            return "success";
        }
    }
}
