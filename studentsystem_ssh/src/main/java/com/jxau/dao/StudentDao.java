package com.jxau.dao;

import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;

import java.util.List;

public interface StudentDao {
    void register(Student student);

    Student findByNameAndPassword(String name, String password);

    void updateNameById(int id, String name);

    Student findById(int id);

    List<Grade> inquiryGradeById(int id);

    List<Announce> inquiryAnnounce();

    Announce findAnnounceById(int id);

    void updatePasswordById(int id, String password);
}
