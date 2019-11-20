package com.jxau.service.controller;

import com.jxau.service.pojo.Student;
import com.jxau.service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("{id}")
    public Student queryStudentById(@PathVariable("id")int id){
        return this.studentService.queryStudentById(id);
    }
}
