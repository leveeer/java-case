package com.jxau.service.impl;

import com.jxau.dao.StudentDao;
import com.jxau.dao.impl.StudentDaoImpl;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void register(Student student) {
        studentDao.register(student);
    }

    @Override
    public Student login(Student student) {
        return studentDao.findByNameAndPassword(student.getName(),student.getPassword());
    }

    @Override
    public void update(String id, String name) {
        studentDao.updateNameById(Integer.parseInt(id),name);
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(Integer.parseInt(id));
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
    public void updatePasswordById(String id, String password) {
        studentDao.updatePasswordById(Integer.parseInt(id),password);
    }
}
