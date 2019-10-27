package com.jxau.test.test;

import com.jxau.test.service.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator extends JFrame implements ActionListener{

    private Function f = new Function();

    public JTextField textField;                        //文本框
    public String[] arr = {"√","xⁿ","C","←",
                            "1","2","3","+",
                            "4","5","6","-",
                            "7","8","9","*",
                            "0",".","=","/"};
    public JButton button[] = new JButton[arr.length];  //按钮
    public String operator = "";                        //运算符
    public String num = "";                             //第一个操作数


    public void calculator() {

        JPanel panelTop = new JPanel();                 //Panel：一个可以承载其他组件的一个面板    Frame：顶层容器
        panelTop.setSize(500,100);
        //System.out.println(panelTop.getBounds());
        textField = new JTextField(10);        //文本框
        textField.setFont(new Font("微软雅黑",0,50));
        textField.setBackground(Color.white);
        textField.setText("0");
        textField.setEditable(false);
        textField.setHorizontalAlignment (JTextField.RIGHT);             //设置输出右对齐
        panelTop.add(textField);

        JPanel panelNum = new JPanel();
        panelNum.setBounds(0,100,500,400);
        panelNum.setLayout(new GridLayout(5, 4,3,3));//设置网格布局  5行4列  ,垂直间距和水平间距：3像素
        for (int i = 0; i < arr.length; i++) {
            button[i] = new JButton(arr[i]);
            button[i].setBackground(Color.lightGray);
            button[i].setForeground(Color.blue);
            button[i].setFont(new Font("Consolas",0,28));
            panelNum.add(button[i]);
            button[i].addActionListener(this);       //为每一个按钮注册监听事件
        }

        this.add(panelTop);
        this.add(panelNum);

        this.setTitle("计算器");
        this.setLayout(null);
        this.setSize(505, 535);             //设置窗口大小    此方法要在setLocationRelativeTo上面
        this.setLocationRelativeTo(null);                  //设置窗口居中
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);                             //显示窗口
        this.setResizable(false);                          //设置不可拉伸大小

    }

    //监听事件
    @Override
    public void actionPerformed(ActionEvent e) {

        if ("0123456789".contains(e.getActionCommand())) {
            if (textField.getText().trim().equals("0")) {
                textField.setText(e.getActionCommand().trim());
            } else {
                textField.setText(textField.getText().concat(e.getActionCommand().trim()));
            }
        } else if (e.getActionCommand() == "."){
            String inputText = textField.getText();
            if (inputText.contains(".")){
                JOptionPane.showMessageDialog(null,"无效输入！","提示",JOptionPane.WARNING_MESSAGE);
            }else {
                textField.setText(textField.getText().concat("."));
            }
        } else if ("+-*/xⁿ√".contains(e.getActionCommand())) {
            num = textField.getText().trim();
            textField.setText("");
            operator = e.getActionCommand().trim();
        } else if (e.getActionCommand() == "=") {
            String num2 = textField.getText().trim();
            if (!operator.trim().equals("")) {
                if (!num2.equals("")) {
                    double result;
                    if (operator.equals("+")) {
                        result = f.add(num, num2);
                        textField.setText(Double.toString(result));
                    } else if (operator.equals("-")) {
                        result = f.dec(num, num2);
                        textField.setText(Double.toString(result));
                    } else if (operator.equals("*")) {
                        result = f.mul(num, num2);
                        textField.setText(Double.toString(result));
                    } else if (operator.equals("/")) {
                        if (num2.equals("0")) {
                            JOptionPane.showMessageDialog(null, "除数不能为0", "消息提示", JOptionPane.WARNING_MESSAGE);
                            textField.setText("0");
                        } else {
                            result = f.div(num, num2);
                            textField.setText(Double.toString(result));
                        }
                    } else if (operator.equals("xⁿ")) {
                        result = f.pow(num, num2);
                        textField.setText(Double.toString(result));
                    } else if (operator.equals("√")) {
                        if ("0".equals(num)) {
                            num = "1";
                            result = f.sqrt(num2);
                            textField.setText(Double.toString(result));
                        }
                    }
                }
            }
        } else if (e.getActionCommand() == "C") {
                int i = JOptionPane.showConfirmDialog(null, "您确定要清空吗！", "消息提示", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_NO_OPTION) {
                    textField.setText("0");
                    num = "";
                }
        } else if (e.getActionCommand() == "←") {
                String s = textField.getText();
                if (s.length() == 1 ){
                    textField.setText("0");
                }else if (s.length() > 0 ) {
                        String substring = s.substring(0, s.length() - 1);
                        textField.setText(substring);
                }
        }
    }



    public static void main(String[] args) {

        Calculator calculater = new Calculator();
        calculater.calculator();
    }



}
