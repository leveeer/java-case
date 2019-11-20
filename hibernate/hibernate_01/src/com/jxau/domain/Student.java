package com.jxau.domain;

import java.io.Serializable;

/**
 * @ClassName Student
 * @Description TODO
 * @Author xie
 * @Date 2019/8/16 08:54
 * @Version 1.0
 */
public class Student implements Serializable {
    private int id;
    private String name;
    private String password;
    private String sex;
    private Integer age;
    private Teacher teacher;

    public Student(){

    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", sex='" + sex + '\'' + ", age=" + age + '}';
    }
}
