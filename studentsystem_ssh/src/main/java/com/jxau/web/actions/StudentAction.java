package com.jxau.web.actions;

import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.service.StudentService;
import com.jxau.service.impl.StudentServiceImpl;
import com.jxau.web.forms.StudentForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StudentAction extends DispatchAction {
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 用户注册
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentForm studentForm = (StudentForm) form;
        Student student = new Student();
        student.setName(studentForm.getName());
        student.setPassword(studentForm.getPassword());
        student.setSex(studentForm.getSex());
        student.setEmail(studentForm.getEmail());
        studentService.register(student);
        return mapping.findForward("register_success");
    }


    /**
     * 学生登录
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentForm studentForm = (StudentForm) form;
        Student student = new Student();
        student.setName(studentForm.getName());
        student.setPassword(studentForm.getPassword());
        Student studentLogin = studentService.login(student);
        if (studentLogin != null){
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("student",studentLogin);
            return mapping.findForward("index");
        }else {
            request.setAttribute("login_error","用户名或密码错误");
            return mapping.findForward("login");
        }
    }

    /**
     * 学生修改信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentForm studentForm = (StudentForm) form;
        int id = studentForm.getId();
        String name = studentForm.getName();

        studentService.update(id,name);
        Student student = studentService.findById(id);
        System.out.println(student);
        request.getSession().setAttribute("student",student);
        return mapping.findForward("SeestudentInfo");
    }

    /**
     * 查询成绩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward scoreInquiry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");

        List<Grade> grade = studentService.inquiryGradeById(id);
        request.setAttribute("grade",grade);
        return mapping.findForward("showgrade");

    }


    /**
     * 查看所有通告
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward announceInquiry(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Announce> list = studentService.inquiryAnnounce();
        request.setAttribute("announces",list);
        return mapping.findForward("StudentAllAnnounce");
    }

    /**
     * 查看通告详情
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findAnnounceById(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Announce announce = studentService.findAnnounceById(id);
        request.setAttribute("announce",announce);

        return mapping.findForward("studentShowAnnounce");
    }


    /**
     * 更新密码
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentForm studentForm = (StudentForm) form;
        int id = studentForm.getId();
        String newPwd = studentForm.getNewPwd();
        studentService.updatePasswordById(id, newPwd);
        return mapping.findForward("logout");
    }

    /**
     * 退出登录
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null){
            return mapping.findForward("login");
        }else {
            session.invalidate();
            return mapping.findForward("login");
        }
    }
}
