package com.jxau.service;

import com.jxau.entity.*;

import java.util.List;

public interface AdminService {
    Admin login(Admin admin);

    List<Student> findStudentInfo();

    void delStudentById(String id);

    Student findStudentById(String id);

    void updateStudentById(Student student);

    void addStudent(Student student);

    List<Grade> studentGrade();

    void gradeDel(String id, String class_id);

    List<Course> findAllCourse();

    void studentGradeAdd(Grade grade);

    List<Announce> findAllAnnounce();

    void addAnnounce(Announce announce);

    Announce findAnnounceById(String id);

    void delAnnounce(String id);

    void updateAnnounce(Announce announce);

    Grade findGradeByStuIdAndClassId(String stu_id, String class_id);

    void updateStudentGrade(Grade grade);

    List<Admin> findAllAdmin();

    void updatePasswordById(int id, String password);

    void courseDel(String class_id);

    void addCourse(Course course);

    Course findCourseByClassId(String class_id);

    void updateCourse(Course course);
}
