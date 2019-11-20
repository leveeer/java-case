package com.jxau.service.service;

import com.jxau.service.mapper.StudentMapper;
import com.jxau.service.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Student queryStudentById(int id){
        return this.studentMapper.selectByPrimaryKey(id);
    }
}
