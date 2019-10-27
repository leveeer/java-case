package com.jxau.plugins;

import com.jxau.utils.DateUtilConvert;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @author xie
 */
public class ConvertPlugIn implements PlugIn {
    @Override
    public void destroy() {

    }

    @Override
    public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {
        System.out.println("ConvertPlugIn的init方法执行了");
        ConvertUtils.register(new DateUtilConvert(), Date.class);
    }
}
