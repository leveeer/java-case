package com.jxau.service.impl;

import com.jxau.dao.AdminDao;
import com.jxau.dao.impl.AdminDaoImpl;
import com.jxau.entity.*;
import com.jxau.service.AdminService;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) {
        return adminDao.findAdminByNameAndPassword(admin.getName(),admin.getPassword());
    }

    @Override
    public List<Student> findStudentInfo() {
        return adminDao.findAllStudentInfo();
    }

    @Override
    public void delStudentById(String id) {
        adminDao.delStudentById(Integer.parseInt(id));
    }

    @Override
    public Student finStudentById(String id) {
        return adminDao.finStudentById(Integer.parseInt(id));
    }

    @Override
    public void updateStudentById(Student student) {
        adminDao.updateStudentById(student);
    }

    @Override
    public void addStudent(Student student) {
        adminDao.addStudent(student);
    }

    @Override
    public List<Grade> studentGrade() {
        return adminDao.findAllStudentGrade();
    }

    @Override
    public void gradeDel(String id, String class_id) {
        adminDao.gradeDel(Integer.parseInt(id), Integer.parseInt(class_id));
    }

    @Override
    public List<Course> findAllCourse() {
        return adminDao.findAllCourse();
    }

    @Override
    public void studentGradeAdd(Grade grade) {
        Student student = adminDao.findStudentByName(grade.getName());
        adminDao.studentGradeAdd(student.getId(),grade.getClass_id(),grade.getGrade());
    }

    @Override
    public List<Announce> findAllAnnounce() {
        return adminDao.findAllAnnounce();
    }

    @Override
    public void addAnnounce(Announce announce) {
        announce.setTime(new Timestamp(System.currentTimeMillis()));
        adminDao.addAnnounce(announce.getHead(),announce.getText(),announce.getTime());
    }

    @Override
    public Announce findAnnounceById(String id) {
        return adminDao.findAnnounceById(Integer.parseInt(id));
    }

    @Override
    public void delAnnounce(String id) {
        adminDao.delAnnounce(Integer.parseInt(id));
    }

    @Override
    public void updateAnnounce(Announce announce) {
        adminDao.updateAnnounce(announce);
    }

    @Override
    public Grade findGradeByStuIdAndClassId(String stu_id, String class_id) {
        Course course = adminDao.findCourseById(Integer.parseInt(class_id));
        Student student = adminDao.findStudentById(Integer.parseInt(stu_id));
        Grade grade = adminDao.findGradeByStuIdAndClassId(Integer.parseInt(stu_id),Integer.parseInt(class_id));
        grade.setClass_name(course.getClass_name());
        grade.setName(student.getName());
        return grade;
    }

    @Override
    public void updateStudentGrade(String stu_id, String class_id, String grade) {
        adminDao.updateStudentGrade(Integer.parseInt(stu_id),Integer.parseInt(class_id),Double.parseDouble(grade));
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminDao.findAllAdmin();
    }

    @Override
    public void updatePasswordById(String id, String password) {
        adminDao.updatePassword(Integer.parseInt(id),password);
    }
}
