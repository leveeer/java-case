package com.jxau.dao;

import com.jxau.entity.*;

import java.sql.Timestamp;
import java.util.List;

public interface AdminDao {
    Admin findAdminByNameAndPassword(String name, String password);

    List<Student> findAllStudentInfo();

    void delStudentById(int id);

    void updateStudentById(Student student);

    void addStudent(Student student);

    List<Grade> findAllStudentGrade();

    void gradeDel(int id, int class_id);

    List<Course> findAllCourse();

    Student findStudentByName(String name);

    void studentGradeAdd(Grade grade);

    List<Announce> findAllAnnounce();

    void addAnnounce(Announce announce);

    Announce findAnnounceById(int id);

    void delAnnounce(int id);

    void updateAnnounce(Announce announce);

    Course findCourseById(int id);

    Student findStudentById(int id);

    Grade findGradeByStuIdAndClassId(int stu_id, int class_id);

    void updateStudentGrade(Grade grade);

    List<Admin> findAllAdmin();

    void updatePassword(int id, String password);

    void courseDel(int class_id);

    void addCourse(Course course);

    void updateCourse(Course course);
}
