package com.jxau.web.controllers;

import com.jxau.domain.Admin;
import com.jxau.domain.User;
import com.jxau.service.AdminService;
import com.jxau.util.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName AdminAction
 * @Description TODO
 * @Author xie
 * @Date 2019/8/19 14:36
 * @Version 1.0
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;

    /**
     * @Author xie
     * @Description //管理员登录
     * @Date  2019/8/19 14:40
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    @RequestMapping("login.do")
    public String login(Admin admin, HttpServletRequest request){

        Admin adminLogin = adminService.login(admin);

        // 判断是否登录成功
        if (adminLogin != null) {

            // 登录成功
            // 将用户信息存入session
            HttpSession session = request.getSession();

            session.setAttribute("admin", adminLogin);

            // 跳转页面
            return "adminIndex";
        } else {

            // 登录失败
            // 将错误信息存入request域
            request.setAttribute("login_error", "用户名或密码错误！");

            // 转发
            return "redirect:/login.jsp";
        }
    }

    /**
     * @Author xie
     * @Description //查询所有用户
     * @Date  2019/8/19 14:42
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    @RequestMapping("findAllUser.do")
    public String findAllUser(String currentPageStr, HttpServletRequest request){

        if (currentPageStr == null){
            currentPageStr = "1";
        }

        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 5;

        PageBean pageBean = (PageBean) adminService.findAllUser(currentPage, currentCount);

        request.setAttribute("pageBean", pageBean);

        return "userlist";
    }

    /**
     * @Author xie
     * @Description //根据条件查询用户
     * @Date  2019/8/19 14:43
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    @RequestMapping("findUserByCondition.do")
    public String findUserByCondition(User user, String currentPageStr, HttpServletRequest request){

        request.setAttribute("username",user.getUsername());

        if (currentPageStr == null){
            currentPageStr = "1";
        }

        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount = 5;

        //获取离线对象
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        //封装查询条件
        dc.add(Restrictions.like("username","%" + user.getUsername() + "%"));
        System.out.println(dc);
        PageBean pageBean =  adminService.findUserByCondition(dc,currentPage,currentCount);
        request.setAttribute("pageBean", pageBean);

        return "userlist";
    }

    /**
     * @Author xie
     * @Description //修改用户状态
     * @Date  2019/8/19 15:11
     * @Param [mapping, form, request, response]
     * @return org.apache.struts.action.ActionForward
     **/
    @RequestMapping("changeLocked.do")
    public void changeLocked(String id, String isLocked, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminService.changLocked(id, isLocked);
        request.getRequestDispatcher("/admin/findAllUser.do").forward(request,response);
    }

    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request){
        Admin adminLogin = (Admin) request.getSession().getAttribute("adminLogin");

        if (adminLogin == null) {
            return "redirect:/login.jsp";
        } else {
            request.getSession().invalidate();
            return "redirect:/login.jsp";
        }
    }
}
