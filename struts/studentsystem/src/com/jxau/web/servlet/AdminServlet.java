package com.jxau.web.servlet;

import com.jxau.entity.*;
import com.jxau.service.AdminService;
import com.jxau.service.impl.AdminServiceImpl;
import com.jxau.util.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdminServlet extends BaseServlet {

    private AdminService adminService = new AdminServiceImpl();

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Admin adminLogin = adminService.login(admin);
        if (adminLogin != null){
            request.getSession().setAttribute("admin",adminLogin);
            response.sendRedirect(request.getContextPath() + "/manage.jsp");
        }else {
            request.setAttribute("login_error","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    /**
     * 全部学生信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findStudentInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Student> list =  adminService.findStudentInfo();
        request.setAttribute("student",list);
        request.getRequestDispatcher("/admin/studentInfo.jsp").forward(request,response);
    }

    /**
     * 删除学生信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void studentDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        adminService.delStudentById(id);
        request.getRequestDispatcher("/adminServlet/findStudentInfo").forward(request,response);
    }

    /**
     * 根据id查找
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = adminService.finStudentById(id);
        request.setAttribute("studentInfo",student);
        request.getRequestDispatcher("/admin/studentUpdate.jsp").forward(request,response);
    }

    /**
     * 更新学生信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminService.updateStudentById(student);
        request.getRequestDispatcher("/adminServlet/findStudentInfo").forward(request,response);
    }

    /**
     * 添加学生
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminService.addStudent(student);
        request.getRequestDispatcher("/adminServlet/findStudentInfo").forward(request,response);
    }

    /**
     * 全部学生成绩
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void studentGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Grade> list = adminService.studentGrade();
        request.setAttribute("grade",list);
        request.getRequestDispatcher("/admin/studentGrade.jsp").forward(request,response);
    }

    /**
     * 删除学生成绩
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void gradeDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String class_id = request.getParameter("class_id");
        adminService.gradeDel(id, class_id);
        request.getRequestDispatcher("/adminServlet/studentGrade").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //查询所有课程信息
     * @Date  2019/8/20 17:12
     * @Param [request, response]
     * @return void 
     **/
    public void findAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = adminService.findAllCourse();
        request.setAttribute("course",courses);
        request.getRequestDispatcher("/admin/studentGradeAdd.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //添加学生成绩
     * @Date  2019/8/20 20:24
     * @Param [request, response]
     * @return void
     **/
    public void studentGradeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Grade grade = new Grade();
        try {
            BeanUtils.populate(grade,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminService.studentGradeAdd(grade);
        request.getRequestDispatcher("/adminServlet/studentGrade").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //查询所有公告
     * @Date  2019/8/20 21:29
     * @Param [request, response]
     * @return void
     **/
    public void findAllAnnounce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Announce> announces = adminService.findAllAnnounce();
       request.setAttribute("announces", announces);
       request.getRequestDispatcher("/admin/announceManager.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //添加公告
     * @Date  2019/8/20 21:45
     * @Param [request, response]
     * @return void
     **/
    public void addAnnounce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Announce announce = new Announce();
        try {
            BeanUtils.populate(announce,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminService.addAnnounce(announce);
        request.getRequestDispatcher("/adminServlet/findAllAnnounce").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //根据id查询通告
     * @Date  2019/8/21 11:02
     * @Param [request, response]
     * @return void
     **/
    public void findAnnounceById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Announce announce = adminService.findAnnounceById(id);
        request.setAttribute("announce",announce);
        request.getRequestDispatcher("/admin/showAnnounce.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //删除通告
     * @Date  2019/8/21 11:02
     * @Param [request, response]
     * @return void
     **/
    public void announceDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        adminService.delAnnounce(id);
        request.getRequestDispatcher("/adminServlet/findAllAnnounce").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //查询通告
     * @Date  2019/8/21 11:02
     * @Param [request, response]
     * @return void
     **/
    public void findAnnounce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Announce announceUpdate = adminService.findAnnounceById(id);
        request.setAttribute("announceUpdate",announceUpdate);

        request.getRequestDispatcher("/admin/updateAnnounce.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //更新通告
     * @Date  2019/8/21 11:03
     * @Param [request, response]
     * @return void
     **/
    public void updateAnnounce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Announce announce = new Announce();
        try {
            BeanUtils.populate(announce,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminService.updateAnnounce(announce);
        request.getRequestDispatcher("/adminServlet/findAllAnnounce").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //通过学生id和课程id查询成绩
     * @Date  2019/8/21 11:03
     * @Param [request, response]
     * @return void
     **/
    public void findGradeByStuIdAndClassId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stu_id = request.getParameter("stu_id");
        String class_id = request.getParameter("class_id");
        Grade grade = adminService.findGradeByStuIdAndClassId(stu_id,class_id);
        request.setAttribute("updateGrade",grade);
        request.getRequestDispatcher("/admin/studentGradeUpdate.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //更新学生成绩
     * @Date  2019/8/21 11:04
     * @Param [request, response]
     * @return void
     **/
    public void updateStudentGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stu_id = request.getParameter("stu_id");
        String class_id = request.getParameter("class_id");
        String grade = request.getParameter("grade");
        adminService.updateStudentGrade(stu_id, class_id, grade);
        request.getRequestDispatcher("/adminServlet/studentGrade").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //查询所有管理员
     * @Date  2019/8/21 11:04
     * @Param [request, response]
     * @return void
     **/
    public void findAllAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = adminService.findAllAdmin();
        request.setAttribute("admins",admins);
        request.getRequestDispatcher("/admin/adminInfo.jsp").forward(request,response);
    }

    /**
     * @Author xie
     * @Description //修改密码
     * @Date  2019/8/21 11:04
     * @Param [request, response]
     * @return void
     **/
    public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("newPwd");
        adminService.updatePasswordById(id,password);
        //跳转页面
        request.getRequestDispatcher("/adminServlet/logout").forward(request,response);
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
