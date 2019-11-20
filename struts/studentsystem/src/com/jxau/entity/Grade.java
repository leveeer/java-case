package com.jxau.entity;

/**
 * 学生成绩实体类
 * @author xie
 */
public class Grade {
    private int class_id;
    private int stu_id;
    private String name;
    private String class_name;
    private String teacher;
    private String score;
    private double grade;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" + "stu_id=" + stu_id + ", name='" + name + '\'' + ", class_name='" + class_name + '\'' + ", teacher='" + teacher + '\'' + ", score='" + score + '\'' + ", grade='" + grade + '\'' + '}';
    }
}
