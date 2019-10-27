package com.jxau.form;

import org.apache.struts.action.ActionForm;

public class NumberForm extends ActionForm {
    private double precedingNum;
    private double backNum;
    private String operator;

    public double getPrecedingNum() {
        return precedingNum;
    }

    public void setPrecedingNum(double precedingNum) {
        this.precedingNum = precedingNum;
    }

    public double getBackNum() {
        return backNum;
    }

    public void setBackNum(double backNum) {
        this.backNum = backNum;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "NumberForm{" + "precedingNum=" + precedingNum + ", backNum=" + backNum + ", operator='" + operator + '\'' + '}';
    }
}
