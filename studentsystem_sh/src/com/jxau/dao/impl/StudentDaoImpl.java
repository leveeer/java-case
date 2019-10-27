package com.jxau.dao.impl;

import com.jxau.dao.StudentDao;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import com.jxau.web.filter.HibernateFilter;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void register(Student student) {
        HibernateFilter.getSession().save(student);
    }

    @Override
    public Student findByNameAndPassword(String name, String password) {
        Query query = HibernateFilter.getSession().createQuery("from Student where name = :name and password = :password");
        query.setString("name",name);
        query.setString("password",password);
        Student student = (Student) query.uniqueResult();
        return student;
    }

    @Override
    public void updateNameById(int id, String name) {
        Session session = HibernateFilter.getSession();
        Student student = session.load(Student.class, id);
        student.setName(name);
        session.save(student);
    }

    @Override
    public Student findById(int id) {
        Student student = HibernateFilter.getSession().load(Student.class, id);
        return student;
    }

    @Override
    public List<Grade> inquiryGradeById(int id) {
        Query query = HibernateFilter.getSession().createQuery("select c.class_name, c.teacher, c.score, g.grade, s.id from Grade g join g.course c join g.student s where s.id = :id");
        query.setParameter("id", id);
        List list = query.list();
        Iterator iterator = list.iterator();
        List<Grade> gradeList = new ArrayList<Grade>();
        while (iterator.hasNext()){
            Object[] o = (Object[]) iterator.next();
            Grade grade = new Grade();
            grade.setClass_name((String)o[0]);
            grade.setTeacher((String)o[1]);
            grade.setScore((String)o[2]);
            grade.setGrade((Double)o[3]);
            grade.setStu_id((Integer)o[4]);
            gradeList.add(grade);
        }
        return gradeList;
    }

    @Override
    public List<Announce> inquiryAnnounce() {
        return HibernateFilter.getSession().createQuery("from Announce").list();
    }

    @Override
    public Announce findAnnounceById(int id) {
        return HibernateFilter.getSession().load(Announce.class, id);
    }

    @Override
    public void updatePasswordById(int id, String password) {
        Session session = HibernateFilter.getSession();
        Student student = session.load(Student.class, id);
        student.setPassword(password);
        session.save(student);
    }
}
