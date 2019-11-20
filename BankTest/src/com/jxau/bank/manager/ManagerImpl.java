package com.jxau.bank.manager;


import com.jxau.bank.model.MoneyBean;
import com.jxau.bank.model.OrderedProperties;
import com.jxau.bank.util.AccountOverDrawnException;
import com.jxau.bank.util.InvalidDepositException;
import com.jxau.bank.util.MD5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ManagerImpl implements ManagerInterface {
    //私有化构造方法，不让其他类创建对象
    private ManagerImpl() {}

    private static ManagerImpl instance = null;

    public static ManagerImpl getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    //创建MoneyBean对象
    MoneyBean mb = MoneyBean.getInstance();
    OrderedProperties properties = new OrderedProperties();

    /**
     * 注册功能
     *
     * @param name
     * @param password
     * @throws IOException
     */
    @Override
    public void register(String name, String password) throws IOException {

        properties.setProperty("name", name);
        properties.setProperty("password", MD5Utils.md5(password));
        properties.setProperty("money", "0.0");

        FileOutputStream fileOutputStream = new FileOutputStream(name + "_info.properties");
        properties.store(fileOutputStream, "User Information");
        fileOutputStream.close();


    }

    /**
     * 登录功能
     *
     * @param loginName
     * @return
     * @throws IOException
     */
    @Override
    public MoneyBean login(String loginName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(loginName + "_info.properties");
        properties.load(fileInputStream);
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        mb.setName(name);
        mb.setPassword(password);
        fileInputStream.close();
        return mb;
    }

    /**
     * 查询余额
     *
     * @return
     */
    public double inquiry() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(mb.getName() + "_info.properties");
        properties.load(fileInputStream);
        String strMoney = properties.getProperty("money");
        double money = Double.parseDouble(strMoney);
        fileInputStream.close();
        return money;
    }

    /**
     * 存款方法
     *
     * @param money
     * @throws InvalidDepositException
     */
    public double deposit(double money) throws InvalidDepositException, IOException {
        if (money <= 0) {
            throw new InvalidDepositException("存款不能为负数！");
        } else {
            String strMoney = properties.getProperty("money");
            double nowMoney = Double.parseDouble(strMoney);
            properties.setProperty("money", Double.toString(nowMoney + money));
            properties.setProperty(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "存入￥", Double.toString(money));
            FileOutputStream fileOutputStream = new FileOutputStream(mb.getName() + "_info.properties");
            properties.store(fileOutputStream, "User Information");
            fileOutputStream.close();
        }
        return money;
    }

    /**
     * 取款方法
     *
     * @param money
     * @throws AccountOverDrawnException
     */
    public double withdrawals(double money) throws AccountOverDrawnException, IOException {
        String strMoney = properties.getProperty("money");
        double nowMoney = Double.parseDouble(strMoney);
        if (money <= 0) {
            System.out.println("您输入的金额有误！");
        } else if (money > nowMoney) {
            throw new AccountOverDrawnException("您的余额不足!");
        } else {
            properties.setProperty("money", Double.toString(nowMoney - money));
            properties.setProperty(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "取出￥", Double.toString(money));
            FileOutputStream fileOutputStream = new FileOutputStream(mb.getName() + "_info.properties");
            properties.store(fileOutputStream, "User Information");
            fileOutputStream.close();
        }
        return money;
    }

    /**
     * 转账功能
     * @param name
     * @param money
     * @return
     */
    @Override
    public void transfer(String name, double money) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(name + "_info.properties");
        OrderedProperties getMoneyProperties = new OrderedProperties();
        getMoneyProperties.load(fileInputStream);
        String strGetMoney = getMoneyProperties.getProperty("money");
        double getMoney = Double.parseDouble(strGetMoney);
        FileOutputStream fileOutputStream = new FileOutputStream(name + "_info.properties");
        getMoneyProperties.setProperty("money", Double.toString(getMoney + money));
        getMoneyProperties.setProperty(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + mb.getName() + "转入￥",Double.toString(money));
        getMoneyProperties.store(fileOutputStream,"User information");
        fileOutputStream.close();
        fileInputStream.close();

        //转账完成，更新金额
        String strNowMoney = properties.getProperty("money");
        double nowMoney = Double.parseDouble(strNowMoney);
        FileOutputStream outputStream = new FileOutputStream(mb.getName() + "_info.properties");
        properties.setProperty("money",Double.toString(nowMoney - money));
        properties.setProperty(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "向" + name + "转出￥",Double.toString(money));
        properties.store(outputStream,"User information");
        outputStream.close();
    }


    /**
     * 退出方法
     */
    public void exitSystem() {
        System.out.println("已成功退出本系统，欢迎下次使用！");
        System.exit(0);
    }
}
