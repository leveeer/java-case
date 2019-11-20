package com.jxau.servlet;

import com.jxau.utils.DateUtilConvert;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionServlet;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @author xie
 */
public class ConvertActionServlet extends ActionServlet {
    @Override
    protected void initOther() throws ServletException {
        super.initOther();
        ConvertUtils.register(new DateUtilConvert(), Date.class);
    }
}
