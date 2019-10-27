package com.jxau.dao.impl;

import com.jxau.dao.AdminDao;
import com.jxau.entity.*;
import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
    @Override
    public Admin findAdminByNameAndPassword(String name, String password) {
        Query query = this.currentSession().createQuery("from Admin where name = :name and password = :password");
        query.setParameter("name",name);
        query.setParameter("password",password);
        Admin admin = (Admin) query.uniqueResult();
        return admin;
    }

    @Override
    public List<Student> findAllStudentInfo() {
        return this.currentSession().createQuery("from Student").list();
    }

    @Override
    public void delStudentById(int id) {
        HibernateTemplate template = this.getHibernateTemplate();
        Student student = template.load(Student.class, id);
        template.delete(student);
    }

    @Override
    public void updateStudentById(Student student) {
        HibernateTemplate template = this.getHibernateTemplate();
        Student stu = template.load(Student.class, student.getId());
        stu.setName(student.getName());
        stu.setPassword(student.getPassword());
        stu.setSex(student.getSex());
        stu.setEmail(student.getEmail());
        template.save(stu);
    }

    @Override
    public void addStudent(Student student) {
        this.getHibernateTemplate().save(student);
    }

    @Override
    public List<Grade> findAllStudentGrade() {

        Query query = this.currentSession().createQuery("select s.id, s.name, c.class_id, c.class_name, c.teacher, c.score, g.grade from Grade g join g.student s join g.course c");
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
        Query query = this.currentSession().createQuery("delete from Grade g where g.student.id = :id and g.course.class_id = :class_id");
        query.setParameter("id",id);
        query.setParameter("class_id",class_id);
        query.executeUpdate();

    }

    @Override
    public List<Course> findAllCourse() {
        return this.currentSession().createQuery("from Course").list();
    }

    @Override
    public Student findStudentByName(String name) {
        Query query = this.currentSession().createQuery("from Student where name = :name");
        query.setParameter("name", name);
        Student student = (Student) query.uniqueResult();
        return student;
    }

    @Override
    public void studentGradeAdd(Grade grade) {
        this.getHibernateTemplate().save(grade);
    }

    @Override
    public List<Announce> findAllAnnounce() {
        return this.currentSession().createQuery("from Announce").list();
    }

    @Override
    public void addAnnounce(Announce announce) {
        this.getHibernateTemplate().save(announce);
    }

    @Override
    public Announce findAnnounceById(int id) {
        return this.getHibernateTemplate().load(Announce.class,id);
    }

    @Override
    public void delAnnounce(int id) {
        HibernateTemplate template = this.getHibernateTemplate();
        Announce announce = template.load(Announce.class, id);
        template.delete(announce);
    }

    @Override
    public void updateAnnounce(Announce announce) {
        HibernateTemplate template = this.getHibernateTemplate();
        Announce anno = template.load(Announce.class, announce.getId());
        anno.setHead(announce.getHead());
        anno.setText(announce.getText());
        template.save(anno);
    }

    @Override
    public Course findCourseById(int id) {
        return this.getHibernateTemplate().load(Course.class,id);
    }

    @Override
    public Student findStudentById(int id) {
        return this.getHibernateTemplate().load(Student.class, id);
    }

    @Override
    public Grade findGradeByStuIdAndClassId(int stu_id, int class_id) {
        Query query = this.currentSession().createQuery("from Grade where stu_id = :stu_id and class_id = :class_id");
        query.setParameter("stu_id",stu_id);
        query.setParameter("class_id",class_id);
        Grade grade = (Grade) query.uniqueResult();
        return grade;
    }

    @Override
    public void updateStudentGrade(Grade grade) {

        Query query = this.currentSession().createQuery("update Grade g set g.grade = :grade where g.student.id = :stu_id and g.course.class_id = :class_id");
        query.setParameter("grade",grade.getGrade());
        query.setParameter("stu_id",grade.getStu_id());
        query.setParameter("class_id",grade.getClass_id());
        query.executeUpdate();
    }

    @Override
    public List<Admin> findAllAdmin() {
        return this.currentSession().createQuery("from Admin").list();
    }

    @Override
    public void updatePassword(int id, String password) {
        HibernateTemplate template = this.getHibernateTemplate();
        Admin admin = template.load(Admin.class, id);
        admin.setPassword(password);
        template.save(admin);
    }

    @Override
    public void courseDel(int class_id) {
        HibernateTemplate template = this.getHibernateTemplate();
        Course course = template.load(Course.class, class_id);
        template.delete(course);
    }

    @Override
    public void addCourse(Course course) {
        this.getHibernateTemplate().save(course);
    }

    @Override
    public void updateCourse(Course course) {

        HibernateTemplate template = this.getHibernateTemplate();
        Course load = template.load(Course.class, course.getClass_id());
        load.setClass_name(course.getClass_name());
        load.setTeacher(course.getTeacher());
        load.setScore(course.getScore());
        template.save(load);
    }
}
