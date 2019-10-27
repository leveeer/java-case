package com.jxau.entity;

/**
 * 课程信息实体类
 * @author xie
 */
public class Course {
    private int class_id;
    private String class_name;
    private String teacher;
    private String score;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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

    @Override
    public String toString() {
        return "Course{" + "class_id=" + class_id + ", class_name='" + class_name + '\'' + ", teacher='" + teacher + '\'' + ", score='" + score + '\'' + '}';
    }
}
