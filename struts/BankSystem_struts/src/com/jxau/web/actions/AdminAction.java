package com.jxau.web.actions;

import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.service.AdminService;
import com.jxau.service.impl.AdminServiceImpl;
import com.jxau.web.forms.OperaForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminAction
 * @Description TODO
 * @Author xie
 * @Date 2019/8/19 14:36
 * @Version 1.0
 */
public class AdminAction extends DispatchAction {
    private AdminService adminService = AdminServiceImpl.getInstance();

    /**
     * @Author xie
     * @Description //管理员登录
     * @Date  2019/8/19 14:40
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        Admin admin     = new Admin();

        admin.setUsername(operaForm.getUsername());
        admin.setPassword(operaForm.getPassword());

        Admin        adminLogin   = adminService.login(admin);

        // 判断是否登录成功
        if (adminLogin != null) {

            // 登录成功
            // 将用户信息存入session
            HttpSession session = request.getSession();

            session.setAttribute("admin", adminLogin);

            // 跳转页面
            return mapping.findForward("adminIndex");
        } else {

            // 登录失败
            // 将错误信息存入request域
            request.setAttribute("login_error", "用户名或密码错误！");

            // 转发
            return mapping.findForward("login");
        }
    }

    /**
     * @Author xie
     * @Description //查询所有用户
     * @Date  2019/8/19 14:42
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward findAllUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        List<User> users        = adminService.findAllUser();

        request.setAttribute("users", users);

        return mapping.findForward("userlist");
    }

    /**
     * @Author xie
     * @Description //根据条件查询用户
     * @Date  2019/8/19 14:43
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward findUserByCondition(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        Map<String, String> condition= new HashMap<String, String>(16);
        condition.put("id",id);
        condition.put("username",username);
        List<User>            users        = adminService.findUser(condition);

        request.setAttribute("users", users);
        request.setAttribute("condition", condition);

        return mapping.findForward("userlist");
    }

    /**
     * @Author xie
     * @Description //修改用户状态
     * @Date  2019/8/19 15:11
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward changeLocked(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String           id           = request.getParameter("id");
        String           isLocked     = request.getParameter("isLocked");
        AdminServiceImpl adminService = AdminServiceImpl.getInstance();

        adminService.changLocked(id, isLocked);

        return mapping.findForward("findAllUser");
    }
}
