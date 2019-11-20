package com.jxau.bank.model;

public class UserBean {
    private int id;
    private String userName;
    private String password;
    private double money;
    private int isBlocked;

    public UserBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(int isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "UserBean{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", money=" + money + ", isBlocked=" + isBlocked + '}';
    }
}
