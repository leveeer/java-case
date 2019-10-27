package com.jxau.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Computer extends Frame implements ActionListener {
    private Button button1 = new Button("1");
    private Button button2 = new Button("2");
    private Button button3 = new Button("3");
    private Button button4 = new Button("4");
    private Button button5 = new Button("5");
    private Button button6 = new Button("6");
    private Button button7 = new Button("7");
    private Button button8 = new Button("8");
    private Button button9 = new Button("9");
    private Button button0 = new Button("0");
    private Button button_point = new Button(".");
    private Button button_add = new Button("+");
    private Button button_reduce = new Button("-");
    private Button button_time = new Button("*");
    private Button button_divide = new Button("/");
    private Button button_equal = new Button("=");
    private Button button_quit = new Button("quit");
    private Button button_clean = new Button("clean");
    private Button button_khz = new Button("(");
    private Button button_khy = new Button(")");
    private Button button_e = new Button("e");

    private TextField result = new TextField();
    private TextField inputDisplay = new TextField();

    private Label label = new Label("本计算器支持加减乘除混和以及乘方等运算");

    // reDisplay 存储着需要计算的表达式
    private String reDisplay = "";

    //构造函数
    Computer(){
        super("Computer ");
        Panel textFiled = new Panel();
        textFiled.setLayout(new GridLayout(3,1));
        textFiled.add(label);
        textFiled.add(inputDisplay);
        textFiled.add(result);
        Panel buttons = new Panel();
        buttons.setLayout(new GridLayout(5,4));
        buttons.add(button_khz);
        buttons.add(button_khy);
        buttons.add(button_clean);
        buttons.add(button_divide);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(button_time);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button_reduce);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button_add);
        buttons.add(button_e);
        buttons.add(button0);
        buttons.add(button_point);
        buttons.add(button_equal);

        Panel equal = new Panel();
        equal.setLayout(new GridLayout(1,0));
        equal.add(button_quit);

        setLayout(new BorderLayout());
        add("North",textFiled);
        add("Center",buttons);
        add("South",equal);

        // 添加监听
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button0.addActionListener(this);
        button_add.addActionListener(this);
        button_reduce.addActionListener(this);
        button_time.addActionListener(this);
        button_divide.addActionListener(this);
        button_equal.addActionListener(this);
        button_quit.addActionListener(this);
        button_clean.addActionListener(this);
        button_point.addActionListener(this);
        button_khz.addActionListener(this);
        button_khy.addActionListener(this);
        button_e.addActionListener(this);
        result.addActionListener(this);
        inputDisplay.addActionListener(this)
        pack();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == button1) {
            reDisplay+="1";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button2) {
            reDisplay+="2";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button3) {
            reDisplay+="3";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button4) {
            reDisplay+="4";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button5) {
            reDisplay+="5";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button6) {
            reDisplay+="6";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button7) {
            reDisplay+="7";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button8) {
            reDisplay+="8";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button9) {
            reDisplay+="9";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button0) {
            reDisplay+="0";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_point) {
            reDisplay+=".";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_add) {
            reDisplay+="+";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_reduce) {
            reDisplay+="-";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_time) {
            reDisplay+="*";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_divide) {
            reDisplay+="/";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_equal) {
            reDisplay+="";
            //Calculator.conversion(reDisplay);
            inputDisplay.setText(reDisplay);
            String ex =""+ Calculate.conversion(reDisplay);
            result.setText(ex);
        }else if(e.getSource() == button_clean) {
            reDisplay ="";
            inputDisplay.setText(reDisplay);
            result.setText(reDisplay);
        }else if(e.getSource() == button_quit) {
            System.exit(0);

        }else if(e.getSource() == button_khz) {
            reDisplay += "(";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_khy) {
            reDisplay += ")";
            inputDisplay.setText(reDisplay);
        }else if(e.getSource() == button_e) {
            reDisplay += "e";
            inputDisplay.setText(reDisplay);
        }
    }
}





