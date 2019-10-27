package com.jxau.service.impl;

import com.jxau.dao.AdminDao;
import com.jxau.entity.*;
import com.jxau.service.AdminService;


import java.sql.Timestamp;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin login(Admin admin) {
        return adminDao.findAdminByNameAndPassword(admin.getName(), admin.getPassword());
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
    public Student findStudentById(String id) {
        return adminDao.findStudentById(Integer.parseInt(id));
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
        grade.setStudent(student);
        Course course = adminDao.findCourseById(grade.getClass_id());
        grade.setCourse(course);
        adminDao.studentGradeAdd(grade);

    }

    @Override
    public List<Announce> findAllAnnounce() {
        return adminDao.findAllAnnounce();
    }

    @Override
    public void addAnnounce(Announce announce) {

        announce.setTime(new Timestamp(System.currentTimeMillis()));
        adminDao.addAnnounce(announce);
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
        Grade grade = adminDao.findGradeByStuIdAndClassId(Integer.parseInt(stu_id), Integer.parseInt(class_id));
        grade.setStu_id(grade.getStudent().getId());
        grade.setClass_name(grade.getCourse().getClass_name());
        grade.setClass_id(grade.getCourse().getClass_id());
        grade.setName(grade.getStudent().getName());
        return grade;
    }

    @Override
    public void updateStudentGrade(Grade grade) {

        adminDao.updateStudentGrade(grade);
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminDao.findAllAdmin();
    }

    @Override
    public void updatePasswordById(int id, String password) {

        adminDao.updatePassword(id, password);

    }

    @Override
    public void courseDel(String class_id) {

        adminDao.courseDel(Integer.parseInt(class_id));
    }

    @Override
    public void addCourse(Course course) {

        adminDao.addCourse(course);
    }

    @Override
    public Course findCourseByClassId(String class_id) {
        return adminDao.findCourseById(Integer.parseInt(class_id));
    }

    @Override
    public void updateCourse(Course course) {

        adminDao.updateCourse(course);
    }
}
