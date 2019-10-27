package com.jxau.web.actions;

import com.jxau.entity.*;
import com.jxau.service.AdminService;
import com.jxau.service.impl.AdminServiceImpl;
import com.jxau.web.forms.AdminForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminAction extends DispatchAction {
    private AdminService adminService = new AdminServiceImpl();

    /**
     * 管理员登录
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Admin admin = new Admin();
        BeanUtils.copyProperties(admin, adminForm);
        Admin adminLogin = adminService.login(admin);
        if (adminLogin != null) {
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("admin", adminLogin);
            return mapping.findForward("manage");
        } else {
            request.setAttribute("login_error", "用户名或密码错误");
            return mapping.findForward("login");
        }
    }

    /**
     * 查询所有学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findStudentInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Student> list = adminService.findStudentInfo();
        request.setAttribute("student", list);
        return mapping.findForward("studentInfo");
    }

    /**
     * 删除学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward studentDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        adminService.delStudentById(id);
        return mapping.findForward("findStudentInfo");
    }


    /**
     * 根据id查询学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findStudentById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        Student student = adminService.findStudentById(id);
        request.setAttribute("studentInfo", student);
        return mapping.findForward("studentUpdate");
    }

    /**
     * 更新学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateStudentById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Student student = new Student();
        BeanUtils.copyProperties(student, adminForm);
        adminService.updateStudentById(student);
        return mapping.findForward("findStudentInfo");
    }

    /**
     * 添加学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addStudent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Student student = new Student();
        BeanUtils.copyProperties(student, adminForm);
        adminService.addStudent(student);
        return mapping.findForward("findStudentInfo");
    }

    /**
     * 全部学生成绩
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward studentGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Grade> list = adminService.studentGrade();
        request.setAttribute("grade", list);
        return mapping.findForward("allStudentGrade");

    }

    /**
     * 删除学生成绩
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward gradeDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String class_id = request.getParameter("class_id");
        adminService.gradeDel(id, class_id);
        return mapping.findForward("studentGrade");
    }

    /**
     * 查询所有课程
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAllCourse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Course> courses = adminService.findAllCourse();
        request.setAttribute("course", courses);
        return mapping.findForward("studentGradeAdd");
    }

    public ActionForward findAllCourses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Course> courses = adminService.findAllCourse();
        request.setAttribute("courses", courses);
        return mapping.findForward("courseManager");
    }

    public ActionForward courseDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String class_id = request.getParameter("class_id");
        adminService.courseDel(class_id);
        return mapping.findForward("courseManager");
    }

    public ActionForward addCourse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Course course = new Course();
        BeanUtils.copyProperties(course,adminForm);
        adminService.addCourse(course);
        return mapping.findForward("findAllCourses");

    }

    public ActionForward findCourseByClassId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String class_id = request.getParameter("class_id");
        Course course = adminService.findCourseByClassId(class_id);
        request.setAttribute("updateCourse",course);
        return mapping.findForward("courseUpdate");
    }

        public ActionForward updateCourse(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AdminForm adminForm = (AdminForm) form;
        Course course = new Course();
        BeanUtils.copyProperties(course, adminForm);
        adminService.updateCourse(course);
        return mapping.findForward("findAllCourses");
    }
        /**
         * 添加学生成绩
         *
         * @param mapping
         * @param form
         * @param request
         * @param response
         * @return
         * @throws Exception
         */
    public ActionForward studentGradeAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Grade grade = new Grade();

        BeanUtils.copyProperties(grade, adminForm);
        adminService.studentGradeAdd(grade);
        return mapping.findForward("studentGrade");
    }

    /**
     * 查询所有公告
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAllAnnounce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Announce> announces = adminService.findAllAnnounce();
        request.setAttribute("announces", announces);
        return mapping.findForward("announceManager");
    }

    /**
     * 添加公告
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAnnounce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AdminForm adminForm = (AdminForm) form;
        Announce announce = new Announce();
        BeanUtils.copyProperties(announce, adminForm);
        adminService.addAnnounce(announce);
        return mapping.findForward("findAllAnnounce");
    }

    /**
     * 根据id查询公告
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAnnounceById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Announce announce = adminService.findAnnounceById(id);
        request.setAttribute("announce", announce);
        return mapping.findForward("showAnnounce");
    }

    /**
     * 删除公告
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward announceDel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        adminService.delAnnounce(id);
        return mapping.findForward("findAllAnnounce");

    }

    /**
     * 根据id查询公告
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAnnounce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Announce announceUpdate = adminService.findAnnounceById(id);
        request.setAttribute("announceUpdate",announceUpdate);
        return mapping.findForward("updateAnnounce");
    }

    /**
     * 更新公告
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateAnnounce(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Announce announce = new Announce();
        BeanUtils.copyProperties(announce,adminForm);
        adminService.updateAnnounce(announce);
        return mapping.findForward("findAllAnnounce");
    }


    /**
     * 根据id和课程id查询学生成绩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findGradeByStuIdAndClassId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stu_id = request.getParameter("stu_id");
        String class_id = request.getParameter("class_id");
        Grade grade = adminService.findGradeByStuIdAndClassId(stu_id,class_id);
        request.setAttribute("updateGrade",grade);
        return mapping.findForward("studentGradeUpdate");
    }

    /**
     * 更新学生成绩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateStudentGrade(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        Grade grade = new Grade();
        BeanUtils.copyProperties(grade, adminForm);
        adminService.updateStudentGrade(grade);
        return mapping.findForward("studentGrade");
    }

    /**
     * 查询所有管理员信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAllAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Admin> admins = adminService.findAllAdmin();
        request.setAttribute("admins",admins);
        return mapping.findForward("adminInfo");
    }

    /**
     * 修改密码
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminForm adminForm = (AdminForm) form;
        int id = adminForm.getId();
        System.out.println(id);
        String newPwd = adminForm.getNewPwd();
        adminService.updatePasswordById(id,newPwd);
        return mapping.findForward("logout");
    }

    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return mapping.findForward("login");
        } else {
            session.invalidate();
            return mapping.findForward("login");
        }

    }

}
