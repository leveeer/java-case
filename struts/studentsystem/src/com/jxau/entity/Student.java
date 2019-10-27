package com.jxau.entity;

/**
 * 学生基本信息实体类
 * @author xie
 */
public class Student {
    private int id;
    private String name;
    private String password;
    private String sex;
    private String email;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", password='" + password + '\'' + ", id=" + id + ", sex='" + sex + '\'' + ", email='" + email + '\'' + '}';
    }
}
