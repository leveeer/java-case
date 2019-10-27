package com.jxau.bank.view;

import com.jxau.bank.model.UserBean;

import javax.swing.*;

public class InquiryFrame extends JFrame {

    JLabel jlb1, jlb2;  //标签
    JTextField jtf1,jtf2;   //文本框
    JPanel jp1,jp2,jp3;     //面板

    UserBean user = new UserBean();
    public void inquiryFrame(double money, String loginName){


        //标签信息

        jlb1 = new JLabel("        姓名");
        jlb2 = new JLabel("        余额");

        jtf1 = new JTextField(13);
        jtf1.setEditable(false);
        jtf2 = new JTextField(13);
        jtf2.setEditable(false);

        jp1 = new JPanel();
        jp2 = new JPanel();

        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jtf2);

        //设置布局
        this.setTitle("查询余额");
        this.setLayout(null);   //采用空布局

        jp1.setBounds(-10, 40, 300, 50);   //-别问我为什么-10 因为 界面好看一点啊
        jp2.setBounds(-10, 110, 300, 50);

        //将JPane加入JFrame中
        this.add(jp1);
        this.add(jp2);

        this.setSize(300, 300);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口

        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小



        jtf1.setText(loginName);   //将信息显示在文本框中
        jtf2.setText(money + "");
    }
}
