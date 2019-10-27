package com.jxau.service.impl;

import com.jxau.dao.StudentDao;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.service.StudentService;


import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void register(Student student) {
        studentDao.register(student);

    }

    @Override
    public Student login(Student student) {
        return studentDao.findByNameAndPassword(student.getName(), student.getPassword());
    }

    @Override
    public void update(int id, String name) {
        studentDao.updateNameById(id, name);

    }

    @Override
    public Student findById(int id) {

        return studentDao.findById(id);

    }

    @Override
    public List<Grade> inquiryGradeById(String id) {

        return studentDao.inquiryGradeById(Integer.parseInt(id));

    }

    @Override
    public List<Announce> inquiryAnnounce() {

        return studentDao.inquiryAnnounce();

    }

    @Override
    public Announce findAnnounceById(String id) {

        return studentDao.findAnnounceById(Integer.parseInt(id));

    }

    @Override
    public void updatePasswordById(int id, String password) {
        studentDao.updatePasswordById(id, password);
    }
}
