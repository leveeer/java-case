package com.jxau.web.listener;

import javax.servlet.http.*;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    private HttpSession session;
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

        System.out.println("移除属性。。。");
        System.out.println(httpSessionBindingEvent.getName() + " : " + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        this.session = httpSessionEvent.getSession();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        System.out.println("销毁了属性。。。");
    }
}
