package com.jxau.listener;

import com.jxau.utils.DateUtilConvert;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * @author xie
 */
public class ConvertListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized方法执行了");
        ConvertUtils.register(new DateUtilConvert(), Date.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
