package com.jxau.web.listener;


import javax.servlet.http.*;

public class ListenerDemo02 implements HttpSessionListener, HttpSessionAttributeListener {

    private HttpSession session;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        this.session = httpSessionEvent.getSession();
        System.out.println("session被创建");
        System.out.println("sessionId:" + this.session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session被销毁");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("增加属性：" + httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("删除属性：" + httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("替换属性：" + httpSessionBindingEvent.getName() + ":" + httpSessionBindingEvent.getValue());

    }
}
