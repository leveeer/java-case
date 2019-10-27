package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.entity.*;
import com.jxau.web.filter.HibernateFilter;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        Query query = HibernateFilter.getSession().createQuery("from Admin where name = :name and password = :password");
        query.setParameter("name",name);
        query.setParameter("password",password);
        Admin admin = (Admin) query.uniqueResult();
        return admin;
    }

    @Override
    public List<Student> findAllStudentInfo() {
        return HibernateFilter.getSession().createQuery("from Student").list();
    }

    @Override
    public void delStudentById(int id) {
        Session session = HibernateFilter.getSession();
        Student student = session.load(Student.class, id);
        session.delete(student);
    }

    @Override
    public void updateStudentById(Student student) {
        Session session = HibernateFilter.getSession();
        Student stu = session.load(Student.class, student.getId());
        stu.setName(student.getName());
        stu.setPassword(student.getPassword());
        stu.setSex(student.getSex());
        stu.setEmail(student.getEmail());
        session.save(stu);
    }

    @Override
    public void addStudent(Student student) {
        HibernateFilter.getSession().save(student);
    }

    @Override
    public List<Grade> findAllStudentGrade() {
        Session session = HibernateFilter.getSession();
        Query query = session.createQuery("select s.id, s.name, c.class_id, c.class_name, c.teacher, c.score, g.grade from Grade g join g.student s join g.course c");
        List list = query.list();
        Iterator iterator = list.iterator();
        List<Grade> grades = new ArrayList<Grade>();
        while (iterator.hasNext()){
            Object[] obj = (Object[]) iterator.next();
            Grade grade = new Grade();
            grade.setStu_id((Integer) obj[0]);
            grade.setName((String) obj[1]);
            grade.setClass_id((Integer) obj[2]);
            grade.setClass_name((String) obj[3]);
            grade.setTeacher((String) obj[4]);
            grade.setScore((String) obj[5]);
            grade.setGrade((Double) obj[6]);
            grades.add(grade);
        }
        return grades;
    }

    @Override
    public void gradeDel(int id, int class_id) {
        Session session = HibernateFilter.getSession();
        Query query = session.createQuery("delete from Grade g where g.student.id = :id and g.course.class_id = :class_id");
        query.setParameter("id",id);
        query.setParameter("class_id",class_id);
        query.executeUpdate();
    }

    @Override
    public List<Course> findAllCourse() {
        return HibernateFilter.getSession().createQuery("from Course").list();
    }

    @Override
    public Student findStudentByName(String name) {
        Query query = HibernateFilter.getSession().createQuery("from Student where name = :name");
        query.setParameter("name", name);
        Student student = (Student) query.uniqueResult();
        return student;
    }

    @Override
    public void studentGradeAdd(Grade grade) {
        HibernateFilter.getSession().save(grade);
    }

    @Override
    public List<Announce> findAllAnnounce() {
        return HibernateFilter.getSession().createQuery("from Announce").list();
    }

    @Override
    public void addAnnounce(Announce announce) {
        HibernateFilter.getSession().save(announce);
    }

    @Override
    public Announce findAnnounceById(int id) {
        return HibernateFilter.getSession().load(Announce.class,id);
    }

    @Override
    public void delAnnounce(int id) {
        Session session = HibernateFilter.getSession();
        Announce announce = session.load(Announce.class, id);
        session.delete(announce);
    }

    @Override
    public void updateAnnounce(Announce announce) {
        Session session = HibernateFilter.getSession();
        Announce anno = session.load(Announce.class, announce.getId());
        anno.setHead(announce.getHead());
        anno.setText(announce.getText());
        session.save(anno);
    }

    @Override
    public Course findCourseById(int id) {
        return HibernateFilter.getSession().load(Course.class,id);
    }

    @Override
    public Student findStudentById(int id) {
        return HibernateFilter.getSession().load(Student.class, id);
    }

    @Override
    public Grade findGradeByStuIdAndClassId(int stu_id, int class_id) {
        Session session = HibernateFilter.getSession();
        Query query = session.createQuery("from Grade where stu_id = :stu_id and class_id = :class_id");
        query.setParameter("stu_id",stu_id);
        query.setParameter("class_id",class_id);
        Grade grade = (Grade) query.uniqueResult();
        return grade;

    }

    @Override
    public void updateStudentGrade(Grade grade) {
        Session session = HibernateFilter.getSession();
        Query query = session.createQuery("update Grade g set g.grade = :grade where g.student.id = :stu_id and g.course.class_id = :class_id");
        query.setParameter("grade",grade.getGrade());
        query.setParameter("stu_id",grade.getStu_id());
        query.setParameter("class_id",grade.getClass_id());
        query.executeUpdate();
    }

    @Override
    public List<Admin> findAllAdmin() {
        return HibernateFilter.getSession().createQuery("from Admin").list();
    }

    @Override
    public void updatePassword(int id, String password) {
        Session session = HibernateFilter.getSession();
        Admin admin = session.load(Admin.class, id);
        admin.setPassword(password);
        session.save(admin);
    }

    @Override
    public void courseDel(int class_id) {
        Session session = HibernateFilter.getSession();
        Course course = session.load(Course.class, class_id);
        session.delete(course);
    }

    @Override
    public void addCourse(Course course) {
        HibernateFilter.getSession().save(course);
    }

    @Override
    public void updateCourse(Course course) {

        Session session = HibernateFilter.getSession();
        System.out.println(course.getClass_id());
        Course load = session.load(Course.class, course.getClass_id());
        load.setClass_name(course.getClass_name());
        load.setTeacher(course.getTeacher());
        load.setScore(course.getScore());
        session.save(load);
    }
}
