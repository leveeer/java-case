package com.jxau.service.impl;

import com.jxau.dao.AdminDao;
import com.jxau.dao.impl.AdminDaoImpl;
import com.jxau.entity.*;
import com.jxau.service.AdminService;
import com.jxau.web.filter.HibernateFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(Admin admin) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Admin adminLogin = adminDao.findAdminByNameAndPassword(admin.getName(), admin.getPassword());
            transaction.commit();
            return adminLogin;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public List<Student> findStudentInfo() {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            List<Student> students = adminDao.findAllStudentInfo();
            transaction.commit();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delStudentById(String id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.delStudentById(Integer.parseInt(id));
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Student findStudentById(String id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Student student = adminDao.findStudentById(Integer.parseInt(id));
            transaction.commit();
            return student;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void updateStudentById(Student student) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.updateStudentById(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void addStudent(Student student) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.addStudent(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<Grade> studentGrade() {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            List<Grade> allStudentGrade = adminDao.findAllStudentGrade();
            transaction.commit();
            return allStudentGrade;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void gradeDel(String id, String class_id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.gradeDel(Integer.parseInt(id), Integer.parseInt(class_id));
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<Course> findAllCourse() {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            List<Course> allCourse = adminDao.findAllCourse();
            transaction.commit();
            return allCourse;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void studentGradeAdd(Grade grade) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            Student student = adminDao.findStudentByName(grade.getName());
            grade.setStudent(student);
            Course course = adminDao.findCourseById(grade.getClass_id());
            grade.setCourse(course);
            adminDao.studentGradeAdd(grade);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }

    @Override
    public List<Announce> findAllAnnounce() {
        return adminDao.findAllAnnounce();
    }

    @Override
    public void addAnnounce(Announce announce) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            announce.setTime(new Timestamp(System.currentTimeMillis()));
            adminDao.addAnnounce(announce);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Announce findAnnounceById(String id) {
        return adminDao.findAnnounceById(Integer.parseInt(id));
    }

    @Override
    public void delAnnounce(String id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.delAnnounce(Integer.parseInt(id));
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void updateAnnounce(Announce announce) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.updateAnnounce(announce);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Grade findGradeByStuIdAndClassId(String stu_id, String class_id) {
        Grade grade = adminDao.findGradeByStuIdAndClassId(Integer.parseInt(stu_id),Integer.parseInt(class_id));
        grade.setStu_id(grade.getStudent().getId());
        grade.setClass_name(grade.getCourse().getClass_name());
        grade.setClass_id(grade.getCourse().getClass_id());
        grade.setName(grade.getStudent().getName());
        return grade;
    }

    @Override
    public void updateStudentGrade(Grade grade) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.updateStudentGrade(grade);
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminDao.findAllAdmin();
    }

    @Override
    public void updatePasswordById(int id, String password) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.updatePassword(id,password);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void courseDel(String class_id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.courseDel(Integer.parseInt(class_id));
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }

    @Override
    public void addCourse(Course course) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.addCourse(course);
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public Course findCourseByClassId(String class_id) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Course course = adminDao.findCourseById(Integer.parseInt(class_id));
            transaction.commit();
            return course;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void updateCourse(Course course) {
        Session session = HibernateFilter.getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            adminDao.updateCourse(course);
            transaction.commit();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
