package com.jxau.action;

import com.jxau.form.LoginActionForm;
import com.jxau.manager.UserManager;
import com.jxau.util.PasswordException;
import com.jxau.util.UserNotFoundException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginActionForm laf = (LoginActionForm) form;
        String username = laf.getUsername();
        String password = laf.getPassword();

        String errorInfo = "";
        try {
            UserManager.getinstance().login(username, password);
            request.setAttribute("LoginForm", laf);
            return mapping.findForward("success");
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
            errorInfo = "用户不能找到";
        } catch (PasswordException pee) {
            pee.printStackTrace();
            errorInfo = "密码错误";
        }
        request.setAttribute("errorInfo", errorInfo);
        return mapping.findForward("error");
    }

}


