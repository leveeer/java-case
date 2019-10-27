package com.jxau.bank.view;

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;
import com.jxau.bank.util.AccountOverTransferException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TransferFrame extends JFrame {
    JButton jb1, jb2;  //按钮
    JLabel jlb1, jlb2; //标签
    JTextField jtf1, jtf2;
    JPanel jp1;

    private ManagerInterface manager = ManagerImpl.getInstance();
    public static boolean flag = true;

    public void transferFrame(String loginName) {

        //添加按钮
        jb1 = new JButton("确定");
        jb2 = new JButton("重置");

        //设置按钮监听
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getMoneyUser = jtf1.getText();
                String strTransferMoney = jtf2.getText();
                transfer(loginName, getMoneyUser, strTransferMoney);

            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        //添加标签
        jlb1 = new JLabel("请输入对方用户名：");
        jlb2 = new JLabel("请输入转账金额：");

        //创建文本框
        jtf1 = new JTextField();            //对方用户名
        jtf2 = new JTextField();            //转出金额

        //对方账户加入面板
        jp1 = new JPanel();
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp1.add(jlb2);
        jp1.add(jtf2);

        //设置布局
        this.setTitle("转账");
        this.setLayout(null);
        this.setSize(400, 300);

        //创建对方账户的标签
        //jp1.setBounds(20, 20, 210, 60);
        //确定和重置按钮
        jlb1.setBounds(30, 45, 120, 20);
        jtf1.setBounds(150, 45, 200, 30);
        jlb2.setBounds(30, 120, 120, 20);
        jtf2.setBounds(150, 120, 200, 30);
        jb1.setBounds(120, 180, 60, 28);
        jb2.setBounds(220, 180, 60, 28);


        this.add(jp1);
        this.add(jlb1);
        this.add(jtf1);
        this.add(jlb2);
        this.add(jtf2);
        this.add(jb1);
        this.add(jb2);

        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口

        this.setVisible(true);  //设置可见
        this.setResizable(false);    //设置不可拉伸大小

    }

    //清空账号和密码框
    private void clear() {
        jtf1.setText("");
        jtf2.setText("");    //设置为空

    }

    private void transfer(String username, String getMoneyUser, String strTransferMoney) {
        try {
            if (getMoneyUser.equals(username)) {
                JOptionPane.showMessageDialog(null, "您不能给自己转账！", "消息提示", JOptionPane.WARNING_MESSAGE);
                clear();
            } else if (Double.parseDouble(strTransferMoney) < 0) {
                JOptionPane.showMessageDialog(null, "转账金额不能为负数！", "消息提示", JOptionPane.WARNING_MESSAGE);
                jtf2.setText("");
            } else {

                boolean flag = manager.transfer(getMoneyUser, Double.parseDouble(strTransferMoney));
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "该用户不存在！", "消息提示", JOptionPane.WARNING_MESSAGE);
                    clear();
                } else {
                    JOptionPane.showMessageDialog(null, "成功向" + getMoneyUser + "转账￥" + Double.parseDouble(strTransferMoney), "消息提示", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "您输入的金额有误，请重新输入！", "消息提示", JOptionPane.WARNING_MESSAGE);
            jtf2.setText("");
        } catch (AccountOverTransferException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "消息提示", JOptionPane.WARNING_MESSAGE);
            jtf2.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
