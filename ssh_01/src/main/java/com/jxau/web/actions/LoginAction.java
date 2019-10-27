package com.jxau.web.actions;

import com.jxau.service.UserService;
import com.jxau.web.forms.LoginForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends Action {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


        LoginForm loginForm = (LoginForm) form;
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        //获取beanFactory,WebApplicationContext是BeanFactory的子类
        /*WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        UserService userService = (UserService) wac.getBean("userService");*/
        boolean flag = userService.login(username,password);
        if (flag){
            return mapping.findForward("success");
        }else {
            return mapping.findForward("login");
        }

    }
}
