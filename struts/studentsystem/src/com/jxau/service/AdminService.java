package com.jxau.service;

import com.jxau.entity.*;

import java.util.List;

public interface AdminService {
    Admin login(Admin admin);

    List<Student> findStudentInfo();

    void delStudentById(String id);

    Student finStudentById(String id);

    void updateStudentById(Student student);

    void addStudent(Student student);

    List<Grade> studentGrade();

    void gradeDel(String id,  String class_id);

    List<Course> findAllCourse();

    void studentGradeAdd(Grade grade);

    List<Announce> findAllAnnounce();

    void addAnnounce(Announce announce);

    Announce findAnnounceById(String id);

    void delAnnounce(String id);

    void updateAnnounce(Announce announce);

    Grade findGradeByStuIdAndClassId(String stu_id, String class_id);

    void updateStudentGrade(String stu_id, String class_id, String grade);

    List<Admin> findAllAdmin();

    void updatePasswordById(String id, String password);
}
