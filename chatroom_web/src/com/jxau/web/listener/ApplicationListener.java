package com.jxau.web.listener;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener {
    private ServletContext application;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.application = servletContextEvent.getServletContext();
        //存放登录用户信息
        Map<String, HttpSession> userMap = new HashMap<String, HttpSession>();
        this.application.setAttribute("userMap",userMap);
        //存放私聊接收者信息
        Map<String, String> toUserMsgMap = new HashMap<>();
        this.application.setAttribute("toUserMsgMap",toUserMsgMap);
        //存放私聊发送者信息
        Map<String, String> fromUserMsgMap = new HashMap<>();
        this.application.setAttribute("fromUserMsgMap",fromUserMsgMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("增加了属性。。。");
        System.out.println(servletContextAttributeEvent.getName() + ":" + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

        System.out.println("删除了属性。。。");
        System.out.println(servletContextAttributeEvent.getName() + ":" + servletContextAttributeEvent.getValue());

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {

        System.out.println("替换了属性。。。");
        System.out.println(servletContextAttributeEvent.getName() + ":" + servletContextAttributeEvent.getValue());

    }


}
