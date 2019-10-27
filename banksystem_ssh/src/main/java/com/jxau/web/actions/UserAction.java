package com.jxau.web.actions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.PageBean;
import com.jxau.web.forms.OperaForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserAction
 * @Description TODO
 * @Author xie
 * @Date 2019/8/19 14:08
 * @Version 1.0
 */
public class UserAction extends DispatchAction {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //注册
     * @Date 2019/8/19 14:17
     * @Param [mapping, form, request, response]
     **/
    public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        User user = new User();

        user.setUsername(operaForm.getUsername());
        user.setPassword(operaForm.getPassword());
        user.setMoney(new BigDecimal("0.00"));

        userService.userRegister(user);

        return mapping.findForward("success");
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //登录
     * @Date 2019/8/19 14:19
     * @Param [mapping, form, request, response]
     **/
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        User user = new User();


        user.setUsername(operaForm.getUsername());
        user.setPassword(operaForm.getPassword());
        System.out.println(user);
        User userLogin = userService.userLogin(user);

        // 判断是否登录成功
        if (userLogin != null) {

            // 登录成功
            // 将用户信息存入session
            HttpSession session = request.getSession();

            session.setAttribute("user", userLogin);

            // 跳转页面
            return mapping.findForward("userIndex");
        } else {

            // 登录失败
            // 将错误信息存入request域
            request.setAttribute("login_error", "用户名或密码错误！");

            // 转发
            return mapping.findForward("login");
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //查询余额
     * @Date 2019/8/19 14:23
     * @Param [mapping, form, request, response]
     **/
    public ActionForward inquiry(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        User user = userService.findUserByUid(id);

        request.setAttribute("user", user);

        return mapping.findForward("inquiry");
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //存款
     * @Date 2019/8/19 14:25
     * @Param [mapping, form, request, response]
     **/
    public ActionForward deposit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        int id = operaForm.getId();
        BigDecimal money = operaForm.getMoney();

        userService.deposit(money, id);
        request.setAttribute("opera_success", "存款成功");

        return mapping.findForward("opera_success");
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //取款
     * @Date 2019/8/19 14:27
     * @Param [mapping, form, request, response]
     **/
    public ActionForward withdraw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        int id = operaForm.getId();

        BigDecimal money = operaForm.getMoney();

        boolean flag = userService.withdraw(id, money);

        if (flag) {
            request.setAttribute("opera_success", "取款成功");

            return mapping.findForward("opera_success");
        } else {
            request.setAttribute("withdraw_error", "您的余额不足！");

            return mapping.findForward("withdraw");
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //转账
     * @Date 2019/8/19 14:30
     * @Param [mapping, form, request, response]
     **/
    public ActionForward transfer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        int id = operaForm.getId();
        BigDecimal money = operaForm.getMoney();
        String getMoneyUser = operaForm.getGetMoneyUser();


        if (userService.findUserByUsername(getMoneyUser) == null) {
            request.setAttribute("transfer_error", "您输入的用户不存在！");

            return mapping.findForward("transfer");
        } else {
            boolean flag = userService.transfer(id, getMoneyUser, money);

            if (flag) {
                request.setAttribute("opera_success", "转账成功");

                return mapping.findForward("opera_success");
            } else {
                request.setAttribute("transfer_error", "您的余额不足");

                return mapping.findForward("transfer");
            }
        }
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //查询明细
     * @Date 2019/8/19 14:32
     * @Param [mapping, form, request, response]
     **/
    public ActionForward record(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null) {
            currentPageStr = "1";
        }
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 5;
        PageBean pageBean = userService.findRecordByPage(id, currentPage, currentCount);

        request.setAttribute("pageBean", pageBean);

        return mapping.findForward("record");
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //修改密码
     * @Date 2019/8/19 14:34
     * @Param [mapping, form, request, response]
     **/
    public ActionForward updatePwd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response)
            throws Exception {
        OperaForm operaForm = (OperaForm) form;
        int id = operaForm.getId();
        String newPwd = operaForm.getNewPwd();

        userService.updatePwdById(id, newPwd);

        return mapping.findForward("login");
    }

    /**
     * @return org.apache.struts.action.ActionForward
     * @Author xie
     * @Description //退出
     * @Date 2019/8/19 14:35
     * @Param [mapping, form, request, response]
     **/
    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        User userLogin = (User) request.getSession().getAttribute("userLogin");

        if (userLogin == null) {
            return mapping.findForward("login");
        } else {
            request.getSession().invalidate();

            return mapping.findForward("login");
        }
    }

    public void findUserByName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("username");
        System.out.println(username);
        User user = userService.findUserByUsername(username);

        //设置响应的数据格式为json
        response.setContentType("application/json;charset=utf-8");

        Map<String, Object> map = new HashMap<String, Object>();

        System.out.println(user);

        if (user == null) {
            //用户不存在
            map.put("userExsit", false);
            map.put("msg", "<img width='35' height='25' src='img/gou.png'/>");
        } else {
            //用户存在
            map.put("userExsit", true);
            map.put("msg", "该用户已存在");
        }

        //将map转为json,传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);

    }
}
