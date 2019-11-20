package com.jxau.bank.view;

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;
import com.jxau.bank.util.InvalidDepositException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMoneyFrame extends JFrame implements ActionListener {
    JButton jb1, jb2, jb3;   //按钮
    JLabel jlb1, jlb2, jlb3; //标签
    JTextField jtf;           //文本框

    private ManagerInterface manager = ManagerImpl.getInstance();

    public void addMoneyFrame() {

        jb1 = new JButton("确定");
        jb2 = new JButton("重置");

        //设置按钮监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);


        jlb1 = new JLabel("请输入存入金额：");  //添加标签

        //创建文本框
        jtf = new JTextField();


        //设置布局
        this.setTitle("存款");
        this.setLayout(null);
        this.setSize(300, 200);

        //存入标签和文本框
        jlb1.setBounds(5, 20, 200, 20);
        jtf.setBounds(20, 50, 250, 30);
        jtf.setFont(new java.awt.Font("Dialog", 0, 15)); //设置字体为字形， 不加粗，15号字体

        //确定和重置按钮
        jb1.setBounds(60, 120, 62, 28);
        jb2.setBounds(160, 120, 62, 28);


        this.add(jlb1);
        this.add(jtf);
        this.add(jb1);
        this.add(jb2);

        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口

        this.setVisible(true);  //设置可见
        this.setResizable(false);    //设置不可拉伸大小

    }

    //清空账号和密码框
    private void clear() {
        jtf.setText("");    //设置为空

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String addMoney = jtf.getText();
        if (e.getActionCommand() == "确定") {
            addMoney(addMoney);

        } else if (e.getActionCommand() == "重置") {
            clear();
        }

    }

    private void addMoney(String addMoney) {
        try {
            manager.deposit(Double.parseDouble(addMoney));
            JOptionPane.showMessageDialog(null, "成功存入￥" + Double.parseDouble(addMoney),"消息提示",JOptionPane.WARNING_MESSAGE);
            dispose();
        } catch (InvalidDepositException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"消息提示",JOptionPane.WARNING_MESSAGE);
            clear();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"您输入的金额有误，请重新输入","消息提示",JOptionPane.WARNING_MESSAGE);
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
