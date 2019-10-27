package com.jxau.bank.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.jxau.bank.manager.ManagerImpl;
import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.model.MoneyBean;
import com.jxau.bank.util.AccountOverDrawnException;
import com.jxau.bank.util.InvalidDepositException;
import com.jxau.bank.util.MD5Utils;

public class TestBank {

    public static void main(String[] args) {

        ManagerInterface mi = ManagerImpl.getInstance();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printWelcomeMenu();
            System.out.println("请选择操作项:");
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    System.out.println("欢迎注册银行系统，请输入个人信息进行注册");
                    System.out.println("请输入姓名：");
                    String name = sc.nextLine();
                    System.out.println("请输入密码：");
                    String password = sc.nextLine();
                    try {
                        File file = new File("D:\\IdeaProjects\\BankTest\\" + name + "_info.properties");
                        if (file.exists()) {
                            System.out.println("该用户已被注册");
                        } else {
                            mi.register(name, password);
                            System.out.println("注册成功！请登录");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.println("请输入姓名：");
                    String loginName = sc.nextLine();
                    System.out.println("请输入密码：");
                    String loginPassword = sc.nextLine();
                    try {
                        MoneyBean mb = mi.login(loginName);
                        if (mb.getName().equals(loginName) && mb.getPassword().equals(MD5Utils.md5(loginPassword))) {
                            System.out.println("登录成功！欢迎您，" + loginName + "，请按照菜单项进行操作");
                            MainMenu();
                        } else {
                            System.out.println("用户名或密码错误！请重试");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("您尚未注册，请先进行注册");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("非法输入，请按照菜单项对应数字进行操作！");
            }

        }
    }

    public static void MainMenu() {
        ManagerInterface mi = ManagerImpl.getInstance();
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("请输入菜单对应数字进行操作:");
            String line = sc.nextLine();
            switch (line) {
                case "1":
                    double money = 0.0;
                    try {
                        money = mi.inquiry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("您的余额为" + money);
                    break;
                case "2":
                    System.out.println("请输入您要存入的金额：");
                    String addMoney = sc.nextLine();
                    try {
                        double putMoney = mi.deposit(Double.parseDouble(addMoney));
                        System.out.println("成功存入￥" + putMoney);
                    } catch (NumberFormatException nfe) {
                        System.out.println("非法输入！请输入存款金额");
                    } catch (InvalidDepositException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("请输入您要取出的金额：");
                    String getMoney = sc.nextLine();
                    try {
                        double getMoneys = mi.withdrawals(Double.parseDouble(getMoney));
                        System.out.println("成功取出￥" + getMoneys);
                    } catch (NumberFormatException nfe) {
                        System.out.println("非法输入！请输入取款金额");
                    } catch (AccountOverDrawnException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "4":
                    System.out.println("请输入要转账的用户：");
                    String getMoneyName = sc.nextLine();
                    System.out.println("请输入转账金额：");
                    String strTransferMoney = sc.nextLine();
                    double transferMoney = Double.parseDouble(strTransferMoney);
                    try {
                    File file = new File("D:\\IdeaProjects\\BankTest\\" + getMoneyName + "_info.properties") ;
                    if (file.exists()){
                        mi.transfer(getMoneyName, transferMoney);
                    }else {
                        System.out.println("该用户不存在！");
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("成功向" + getMoneyName + "转账￥" + strTransferMoney);
                    break;
                case "5":
                    mi.exitSystem();
                default:
                    System.out.println("非法输入，请按照菜单项对应数字进行操作！");
            }
        }
    }

    private static void printWelcomeMenu() {
        System.out.println("===============银行系统1.4===============");
        System.out.println("|              1.注     册              |");
        System.out.println("|              2.登     录              |");
        System.out.println("=================欢迎使用================");
    }

    /**
     * 打印菜单
     */
    public static void printMenu() {
        System.out.println("===============银行系统1.4===============");
        System.out.println("|              1.查询余额               |");
        System.out.println("|              2.存    款               |");
        System.out.println("|              3.取    款               |");
        System.out.println("|              4.转    账               |");
        System.out.println("|              5.退出系统               |");
        System.out.println("=================欢迎使用================");
    }
}
