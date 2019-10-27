package com.jxau.test.service;

public class Function {

    /**
     * 加法运算
     * @param num
     * @param textFieldNum
     * @return
     */
    public double add(String num, String textFieldNum) {
        double num1 = Double.parseDouble(num);
        double num2 = Double.parseDouble(textFieldNum);
        return num1 + num2;
    }


    /**
     * 减法运算
     * @param num
     * @param textFieldNum
     * @return
     */
    public double dec(String num, String textFieldNum) {
        double num1 = Double.parseDouble(num);
        double num2 = Double.parseDouble(textFieldNum);
        return num1 - num2;
    }

    /**
     * 乘法运算
     * @param num
     * @param textFieldNum
     * @return
     */
    public double mul(String num, String textFieldNum) {
        double num1 = Double.parseDouble(num);
        double num2 = Double.parseDouble(textFieldNum);
        return num1 * num2;
    }

    /**
     * 除法运算
     * @param num
     * @param textFieldNum
     * @return
     */
    public double div(String num, String textFieldNum) {
        double num1 = Double.parseDouble(num);
        double num2 = Double.parseDouble(textFieldNum);
        return num1 / num2;
    }

    /**
     * 指数运算
     * @param num
     * @param textFieldNum
     * @return
     */
    public double pow(String num, String textFieldNum) {
        double num1 = Double.parseDouble(num);
        double num2 = Double.parseDouble(textFieldNum);
        return Math.pow(num1,num2);
    }

    /**
     * 根号运算
     * @param textFieldNum
     * @return
     */
    public double sqrt(String textFieldNum) {
        double num2 = Double.parseDouble(textFieldNum);
        return Math.sqrt(num2);
    }
}
