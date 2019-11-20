package com.jxau.service.impl;

import com.jxau.dao.StudentDao;
import com.jxau.dao.impl.StudentDaoImpl;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.service.StudentService;
import com.jxau.web.filter.HibernateFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void register(Student student) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            studentDao.register(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Student login(Student student) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Student studentLogin = studentDao.findByNameAndPassword(student.getName(), student.getPassword());
            transaction.commit();
            return studentLogin;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void update(int id, String name) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            studentDao.updateNameById(id, name);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Student findById(int id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Student student = studentDao.findById(id);
            transaction.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public List<Grade> inquiryGradeById(String id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            List<Grade> grades = studentDao.inquiryGradeById(Integer.parseInt(id));
            transaction.commit();
            return grades;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public List<Announce> inquiryAnnounce() {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            List<Announce> announces = studentDao.inquiryAnnounce();
            transaction.commit();
            return announces;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Announce findAnnounceById(String id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Announce announce = studentDao.findAnnounceById(Integer.parseInt(id));
            transaction.commit();
            return announce;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void updatePasswordById(int id, String password) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            studentDao.updatePasswordById(id,password);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }
}
