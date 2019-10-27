package com.jxau.service;

import com.jxau.entity.Announce;
import com.jxau.entity.Grade;
import com.jxau.entity.Student;

import java.util.List;

public interface StudentService {

    void register(Student student);

    Student login(Student student);

    void update(String id, String name);

    Student findById(String id);

    List<Grade> inquiryGradeById(String id);

    List<Announce> inquiryAnnounce();

    Announce findAnnounceById(String id);

    void updatePasswordById(String id, String password);
}
