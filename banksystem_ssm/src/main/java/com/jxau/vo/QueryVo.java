package com.jxau.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class QueryVo implements Serializable {
    private Integer id;
    private String username;
    private Integer currentPage;
    private String getMoneyUser;
    private BigDecimal money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getGetMoneyUser() {
        return getMoneyUser;
    }

    public void setGetMoneyUser(String getMoneyUser) {
        this.getMoneyUser = getMoneyUser;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
