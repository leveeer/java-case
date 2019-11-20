package com.jxau.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private BigDecimal money;
    private Integer isLocked;
    private String isLockedStr;

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

    public String getIsLockedStr() {
        if (isLocked != null) {
            if (isLocked == 0) {
                isLockedStr = "未冻结";
            }
            if (isLocked == 1) {
                isLockedStr = "已冻结";
            }
        }
        return isLockedStr;
    }

    public void setIsLockedStr(String isLockedStr) {
        this.isLockedStr = isLockedStr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", isLocked=" + isLocked +
                '}';
    }
}
