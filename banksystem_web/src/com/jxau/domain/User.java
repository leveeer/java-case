package com.jxau.domain;

import java.math.BigDecimal;

public class User {
    private int id;
    private String username;
    private String password;
    private BigDecimal money;
    private int isLocked;

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

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", money=" + money + ", isLocked=" + isLocked + '}';
    }
}
