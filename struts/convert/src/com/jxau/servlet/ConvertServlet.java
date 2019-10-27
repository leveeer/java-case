package com.jxau.servlet;

import com.jxau.utils.DateUtilConvert;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.Date;

/**
 * @author xie
 */
public class ConvertServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        System.out.println("ConvertServlet的init方法执行了");
        ConvertUtils.register(new DateUtilConvert(), Date.class);
    }
}
