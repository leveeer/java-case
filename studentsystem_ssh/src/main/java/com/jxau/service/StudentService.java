package com.jxau.service;

import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;

import java.util.List;

public interface StudentService {

    void register(Student student);

    Student login(Student student);

    void update(int id, String name);

    Student findById(int id);

    List<Grade> inquiryGradeById(String id);

    List<Announce> inquiryAnnounce();

    Announce findAnnounceById(String id);

    void updatePasswordById(int id, String password);
}
