package com.jxau.web.listener;

import javax.servlet.*;

public class ListenerDemo01 implements ServletContextListener, ServletContextAttributeListener {
    private  ServletContext application = null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.application = servletContextEvent.getServletContext();
        System.out.println("上下文初始化.....");
        System.out.println("当前虚拟目录为" + application.getRealPath("/"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("上下文销毁.....");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("增加属性：" + servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("删除属性：" + servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("替换属性：" + servletContextAttributeEvent.getName() + " = " + servletContextAttributeEvent.getValue());

    }
}
