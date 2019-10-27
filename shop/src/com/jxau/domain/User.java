package com.jxau.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 09:10
 * @Version 1.0
 */
public class User implements Serializable {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private Date birthday;
    private String sex;
    //用户激活状态
    private int state;
    //用户激活码
    private String code;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" + "uid='" + uid + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + ", telephone='" + telephone + '\'' + ", birthday=" + birthday + ", sex='" + sex + '\'' + ", state='" + state + '\'' + ", code='" + code + '\'' + '}';
    }
}
