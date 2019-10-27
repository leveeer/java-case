package com.jxau.domain;

import java.math.BigDecimal;

public class User {
    private int id;
    private String username;
    private String password;
    private BigDecimal money;
    private int isLocked;
    private String getMoneyUser;
    private String newPwd;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }

    public String getGetMoneyUser() {
        return getMoneyUser;
    }

    public void setGetMoneyUser(String getMoneyUser) {
        this.getMoneyUser = getMoneyUser;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", isLocked=" + isLocked +
                ", getMoneyUser='" + getMoneyUser + '\'' +
                ", newPwd='" + newPwd + '\'' +
                '}';
    }
}
