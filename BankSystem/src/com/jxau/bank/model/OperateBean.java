package com.jxau.bank.model;

import java.sql.Timestamp;

public class OperateBean {
    private int id;             //用户id
    private String Ousername;   //用户名
    private String Otype;       //操作类型
    private Timestamp Otime;    //操作时间
    private double Omoney;      //操作金额
    private double OBmoney;     //上次余额
    private double Amoney;      //本次余额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOusername() {
        return Ousername;
    }

    public void setOusername(String ousername) {
        Ousername = ousername;
    }

    public String getOtype() {
        return Otype;
    }

    public void setOtype(String otype) {
        Otype = otype;
    }

    public Timestamp getOtime() {
        return Otime;
    }

    public void setOtime(Timestamp otime) {
        Otime = otime;
    }

    public double getOmoney() {
        return Omoney;
    }

    public void setOmoney(double omoney) {
        Omoney = omoney;
    }

    public double getOBmoney() {
        return OBmoney;
    }

    public void setOBmoney(double OBmoney) {
        this.OBmoney = OBmoney;
    }

    public double getAmoney() {
        return Amoney;
    }

    public void setAmoney(double amoney) {
        Amoney = amoney;
    }

    @Override
    public String toString() {
        return "OperateBean{" + "id=" + id + ", Ousername='" + Ousername + '\'' + ", Otype='" + Otype + '\'' + ", Otime=" + Otime + ", Omoney=" + Omoney + ", OBmoney=" + OBmoney + ", Amoney=" + Amoney + '}';
    }
}
