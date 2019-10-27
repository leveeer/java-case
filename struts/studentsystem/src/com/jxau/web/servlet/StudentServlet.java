package com.jxau.web.servlet;

import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.service.StudentService;
import com.jxau.service.impl.StudentServiceImpl;
import com.jxau.util.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xie
 */
public class StudentServlet extends BaseServlet {

    private StudentService studentService = new StudentServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //封装数据
        Map<String, String[]> map = request.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用Service完成注册
        studentService.register(student);
        //注册成功，跳转页面
        response.sendRedirect(request.getContextPath() + "/register_success.jsp");
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //封装数据
        Map<String, String[]> map = request.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用service查询
        Student studentLogin = studentService.login(student);
        //判断是否登录成功
        if (studentLogin != null){
            //登录成功,将用户信息保存在session中
            HttpSession session = request.getSession();
            session.setAttribute("student",studentLogin);
            //跳转页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
            //登录失败
            request.setAttribute("login_error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    /**
     * 修改姓名
     * @param request
     * @param response
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        /*System.out.println(id);
        System.out.println(name);*/
        //调用service修改
        studentService.update(id,name);
        Student student = studentService.findById(id);
        request.getSession().setAttribute("student",student);
        //跳转页面
        response.sendRedirect(request.getContextPath() + "/student/SeestudentInfo.jsp");
    }

    /**
     * 成绩查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void scoreInquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        //调用service查询
        List<Grade> grade = studentService.inquiryGradeById(id);
        request.setAttribute("grade",grade);
        request.getRequestDispatcher("/student/showgrade.jsp").forward(request,response);
    }

    /**
     * 查询所有通告
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void announceInquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Announce> list = studentService.inquiryAnnounce();
        request.setAttribute("announces",list);
        request.getRequestDispatcher("/student/StudentAllAnnounce.jsp").forward(request,response);
    }

    /**
     * 查看通告详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAnnounceById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Announce announce = studentService.findAnnounceById(id);
        request.setAttribute("announce",announce);
        request.getRequestDispatcher("/student/studentShowAnnounce.jsp").forward(request,response);
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws IOException
     */
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String password = request.getParameter("newPwd");
        studentService.updatePasswordById(id,password);
        Student student = studentService.findById(id);
        request.getSession().setAttribute("student",student);
        //跳转页面
        response.sendRedirect(request.getContextPath() + "/student/SeestudentInfo.jsp");
    }

    /**
     * @Author xie
     * @Description //退出
     * @Date  2019/8/21 10:13
     * @Param [request, response]
     * @return void
     **/
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

}
