package com.jxau.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Log implements Serializable {
    private int opId;
    private String opType;
    private Date opTime;
    private BigDecimal opMoney;
    private BigDecimal beforeMoney;
    private BigDecimal currentMoney;
    private User user;

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public BigDecimal getOpMoney() {
        return opMoney;
    }

    public void setOpMoney(BigDecimal opMoney) {
        this.opMoney = opMoney;
    }

    public BigDecimal getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(BigDecimal beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(BigDecimal currentMoney) {
        this.currentMoney = currentMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Log{" + "opId=" + opId + ", opType='" + opType + '\'' + ", opTime=" + opTime + ", opMoney=" + opMoney + ", beforeMoney=" + beforeMoney + ", currentMoney=" + currentMoney + ", user=" + user + '}';
    }
}
