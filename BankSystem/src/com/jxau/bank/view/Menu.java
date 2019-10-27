
package com.jxau.bank.view;
/*
import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;
import com.jxau.bank.util.AccountOverDrawnException;
import com.jxau.bank.util.InvalidDepositException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestBank {
    public static void main(String[] args) {
        //获取业务层对象
        ManagerImpl manager = ManagerImpl.getInstance();
        //创建键盘输入对象
        Scanner sc = new Scanner(System.in);

        while (true) {
            printWelcomeMenu();
            System.out.println("欢迎使用银行系统，请按照菜单项进行操作");
            //接收用户输入信息
            String line = sc.nextLine();
            switch (line) {
                case "1":
                    System.out.println("欢迎注册银行系统，请输入个人信息进行注册");
                    System.out.println("请输入用户名：");
                    String registerName = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password = sc.nextLine();
                    File file = new File(registerName + ".properties");
                    try {
                        if (file.exists())
                            System.out.println("该用户已注册！");
                        else {
                            boolean registerFlag = manager.register(registerName, password);
                            if (!registerFlag) {
                                System.out.println("用户名或密码不能为空");
                            } else {
                                System.out.println("注册成功！");
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.println("请输入用户名：");
                    String loginName = sc.nextLine();
                    System.out.println("请输入密码：");
                    String loginPassword = sc.nextLine();
                    try {
                        boolean flag = manager.login(loginName, loginPassword);
                        if (!flag) {
                            System.out.println("用户名或密码错误！请重试！");
                        } else {
                            System.out.println("登录成功！欢迎您，" + loginName);
                            MainMenu();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("已成功退出本系统，欢迎下次使用");
                    System.exit(0);
                default:
                    System.out.println("您的输入有误！请按照菜单项进行操作");
            }
        }
    }

    private static void MainMenu() {

        ManagerInterface manager = ManagerImpl.getInstance();
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("请输入菜单对应数字进行操作:");
            String line = sc.nextLine();
            switch (line) {
                case "1":
                    double money = 0;
                    try {
                        money = manager.inquiry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("您的余额为￥" + money);
                    break;
                case "2":
                    System.out.println("请输入您要存入的金额：");
                    String addMoney = sc.nextLine();
                    try {
                        manager.deposit(Double.parseDouble(addMoney));
                        System.out.println("成功存入￥" + Double.parseDouble(addMoney));
                    } catch (InvalidDepositException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("您输入的金额有误！请输入正确金额");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("请输入您要取出的金额：");
                    String getMoney = sc.nextLine();
                    try {
                        manager.withdrawals(Double.parseDouble(getMoney));
                        System.out.println("成功取出￥" + Double.parseDouble(getMoney));
                    } catch (NumberFormatException nfe) {
                        System.out.println("您输入的金额有误！请输入正确金额");
                    } catch (AccountOverDrawnException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.println("请输入要转账的用户：");
                    String getMoneyUser = sc.nextLine();
                    System.out.println("请输入转账金额：");
                    String strTransferMoney = sc.nextLine();
                    File file = new File(getMoneyUser + ".properties");
                    try {
                        if (file.exists()) {
                            manager.transfer(getMoneyUser, Double.parseDouble(strTransferMoney));
                        } else {
                            System.out.println("该用户不存在！");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e){
                        System.out.println("您输入的金额有误，请重试！");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    try {
                        System.out.println("已成功退出本系统，欢迎下次使用");
                        manager.exitSystem();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                default:
                    System.out.println("非法输入，请按照菜单项对应数字进行操作！");
            }
        }
    }

    private static void printWelcomeMenu() {
        System.out.println("===============银行系统1.5===============");
        System.out.println("|              1.注     册              |");
        System.out.println("|              2.登     录              |");
        System.out.println("|              3.退     出              |");
        System.out.println("=================欢迎使用================");
    }

    public static void printMenu() {
        System.out.println("===============银行系统1.5===============");
        System.out.println("|              1.查询余额               |");
        System.out.println("|              2.存    款               |");
        System.out.println("|              3.取    款               |");
        System.out.println("|              4.转    账               |");
        System.out.println("|              5.退出系统               |");
        System.out.println("=================欢迎使用================");
    }
}
*/

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class Menu extends JFrame{

        private ManagerInterface manager = ManagerImpl.getInstance();

        JButton jb1, jb2, jb3, jb4, jb5, jb6;  //创建按钮
        JLabel jlb1, jlb2, jlb3;                //标签

        public void Menu(String loginName, String loginPassword) {

                jb1 = new JButton("查询");
                jb1.setFont(new Font("Microsoft JhengHei",1,20));
                jb2 = new JButton("存款");
                jb2.setFont(new Font("Microsoft JhengHei",1,20));
                jb3 = new JButton("取款");
                jb3.setFont(new Font("Microsoft JhengHei",1,20));
                jb4 = new JButton("转账");
                jb4.setFont(new Font("Microsoft JhengHei",1,20));
                jb5 = new JButton("记录");
                jb5.setFont(new Font("Microsoft JhengHei",1,20));
                jb6 = new JButton("退卡");
                jb6.setFont(new Font("Microsoft JhengHei",1,20));


                jlb1 = new JLabel("银行系统1.9");
                jlb1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 26)); //设置字体类型， 是否加粗，字号
                jlb2 = new JLabel("欢迎您" + loginName);
                jlb2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 18));
                jlb3 = new JLabel("请您选择服务");
                jlb3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 22));

                //事件监听
                //查询事件
                jb1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                double money = 0;
                                try {
                                        money = manager.inquiry();
                                } catch (IOException ex) {
                                        ex.printStackTrace();
                                }
                                new InquiryFrame().inquiryFrame(money,loginName);
                        }
                });

                //存款事件
                jb2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                          if (manager.findUserByName(loginName).getIsBlocked() == 1){
                              JOptionPane.showMessageDialog(null,"您的账户已被锁定!","消息提示",JOptionPane.WARNING_MESSAGE);
                          }else {
                              new AddMoneyFrame().addMoneyFrame();
                          }
                        }
                });

                //取款
                jb3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (manager.findUserByName(loginName).getIsBlocked() == 1){
                                JOptionPane.showMessageDialog(null,"您的账户已被锁定!","消息提示",JOptionPane.WARNING_MESSAGE);
                            }else {
                                new GetMoneyFrame().getMoneyFrame();
                            }
                        }
                });

                //转账
                jb4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (manager.findUserByName(loginName).getIsBlocked() == 1) {
                                JOptionPane.showMessageDialog(null, "您的账户已被锁定!", "消息提示", JOptionPane.WARNING_MESSAGE);
                            } else {

                                new TransferFrame().transferFrame(loginName);
                            }
                        }
                });

                //交易记录
                jb5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new RecordFrame().recordFrame(loginName);

                        }
                });

                //退卡事件
                jb6.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        manager.exitSystem();
                                        System.exit(0);
                                } catch (IOException ex) {
                                        ex.printStackTrace();
                                }
                                dispose();
                        }
                });


                this.setTitle("银行管理系统");  //设置窗体标题
                this.setSize(450, 400);         //设置窗体大小
                this.setLocation(400, 200);     //设置位置
                this.setLayout(null);           //设置布局，不采用布局


                //设置按钮的位置和大小
                jb1.setBounds(0, 50, 90, 60);
                jb2.setBounds(0, 150, 90, 60);
                jb3.setBounds(0, 250, 90, 60);
                jb4.setBounds(354, 50, 90, 60);
                jb5.setBounds(354, 150, 90, 60);
                jb6.setBounds(354, 250, 90, 60);

                //设置标签的位置和大小
                jlb1.setBounds(150, 20, 150, 50);
                jlb2.setBounds(170, 130, 270, 50);
                jlb3.setBounds(150, 250, 150, 50);

                this.add(jb1);   //加入窗体
                this.add(jb2);
                this.add(jb3);
                this.add(jb4);
                this.add(jb5);
                this.add(jb6);
                this.add(jlb1);
                this.add(jlb2);
                this.add(jlb3);

                this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  //设置关闭前确认
                addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                                super.windowClosing(e);
                                int result = JOptionPane.showConfirmDialog(null,"确认要退出系统吗？","退出系统",JOptionPane.YES_NO_OPTION);
                                if (result == JOptionPane.YES_OPTION){
                                        try {
                                                manager.exitSystem();
                                        } catch (IOException ex) {
                                                ex.printStackTrace();
                                        }
                                }
                        }
                });

                this.setVisible(true);  //设置可见
                this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
                this.setResizable(false);   //设置不可拉伸大小
        }
}

