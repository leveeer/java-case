package com.jxau.controller;


import com.jxau.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 常用注解
 */
@Controller
@RequestMapping(path = "/anno")
@SessionAttributes(value = {"msg"})         //把mas=张三存入到session域中
public class AnnoController {


    /**
     * @RequestParam(name = "username", required = true)
     * name属性和jsp页面传递的参数名一致
     * required：请求参数中是否必须提供此参数。默认值：true。表示必须提供，如果不提供将报错。
     * @param name
     * @return
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "username", required = true) String name){
        System.out.println(name);
        return "success";
    }

    /**
     * 获取请求体内容
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println(body);
        return "success";
    }


    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable(name = "id") String uid) {
        System.out.println(uid);
        return "success";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println(cookieValue);
        return "success";
    }

    /*@RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user) {
        System.out.println("testModelAttribute执行了");
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public User showUser(String uname){
        System.out.println("showUser执行了");
        //通过用户名查询数据库
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setBirthday(new Date());
        return user;
    }*/

    @ModelAttribute
    public void showUser(String uname, Map<String, User> map){
        System.out.println("showUser执行了");
        //通过用户名查询数据库
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setBirthday(new Date());
        map.put("user", user);
    }
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute(value = "user") User user) {
        System.out.println("testModelAttribute执行了");
        System.out.println(user);
        return "success";
    }


    @RequestMapping("/setSessionAttributes")
    public String setSessionAttributes(Model model) {
        System.out.println("setSessionAttributes执行了");
        model.addAttribute("msg","张三");
        return "success";
    }

    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap model) {
        System.out.println("getSessionAttributes执行了");
        String msg = (String) model.get("msg");
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("/deleteSessionAttributes")
    public String deleteSessionAttributes(SessionStatus status) {
        System.out.println("deleteSessionAttributes");
        status.setComplete();
        return "success";
    }



}
