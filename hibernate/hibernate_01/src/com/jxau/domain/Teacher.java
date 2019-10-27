package com.jxau.domain;

import java.io.Serializable;

/**
 * @ClassName Teacher
 * @Description 教师实体类
 * @Author xie
 * @Date 2019/8/16 14:26
 * @Version 1.0
 */
public class Teacher implements Serializable {
    private int tid;
    private String tname;
    private String tpassword;
    private String tclass;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    @Override
    public String toString() {
        return "Teacher{" + "tid=" + tid + ", tname='" + tname + '\'' + ", tpassword='" + tpassword + '\'' + ", tclass='" + tclass + '\'' + '}';
    }
}
