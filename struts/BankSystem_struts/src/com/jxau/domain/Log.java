package com.jxau.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Log {
    private int opId;
    private int uid;
    private String username;
    private String opType;
    private Timestamp opTime;
    private BigDecimal opMoney;
    private BigDecimal beforeMoney;
    private BigDecimal currentMoney;

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Timestamp getOpTime() {
        return opTime;
    }

    public void setOpTime(Timestamp opTime) {
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

    @Override
    public String toString() {
        return "Log{" + "opId=" + opId + ", uid=" + uid + ", username='" + username + '\'' + ", opType='" + opType + '\'' + ", opTime=" + opTime + ", opMoney=" + opMoney + ", beforeMoney=" + beforeMoney + ", currentMoney=" + currentMoney + '}';
    }
}
