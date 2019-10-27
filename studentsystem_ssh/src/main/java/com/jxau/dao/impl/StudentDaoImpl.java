package com.jxau.dao.impl;

import com.jxau.dao.StudentDao;
import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
    @Override
    public void register(Student student) {
        this.getHibernateTemplate().save(student);
    }

    @Override
    public Student findByNameAndPassword(String name, String password) {
        Query query = this.currentSession().createQuery("from Student where name = :name and password = :password");
        query.setParameter("name",name);
        query.setParameter("password",password);
        Student student = (Student) query.uniqueResult();
        return student;
    }

    @Override
    public void updateNameById(int id, String name) {
        HibernateTemplate template = this.getHibernateTemplate();
        Student student = template.load(Student.class, id);
        student.setName(name);
        template.save(student);
    }

    @Override
    public Student findById(int id) {
        return this.getHibernateTemplate().load(Student.class, id);

    }

    @Override
    public List<Grade> inquiryGradeById(int id) {
        Query query = this.currentSession().createQuery("select c.class_name, c.teacher, c.score, g.grade, s.id from Grade g join g.course c join g.student s where s.id = :id");
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
        return this.currentSession().createQuery("from Announce").list();
    }

    @Override
    public Announce findAnnounceById(int id) {
        return this.getHibernateTemplate().load(Announce.class, id);
    }

    @Override
    public void updatePasswordById(int id, String password) {
        HibernateTemplate template = this.getHibernateTemplate();
        Student student = template.load(Student.class, id);
        student.setPassword(password);
        template.save(student);
    }
}
