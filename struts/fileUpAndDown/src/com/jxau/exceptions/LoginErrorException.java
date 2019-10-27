package com.jxau.exceptions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;

/**
 * @author : xie
 * @version : 1.0
 * @description : TODO
 * @date : 2019/8/20
 * @see JavaDoc
 */
public class LoginErrorException extends ExceptionHandler {
    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException {
        return super.execute(ex, ae, mapping, formInstance, request, response);
    }
}
