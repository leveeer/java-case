package com.jxau.actions;

import com.jxau.form.NumberForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NumberAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NumberForm numberForm = (NumberForm) form;
        request.setAttribute("numberForm",numberForm);
        return mapping.findForward("index");
    }
}
