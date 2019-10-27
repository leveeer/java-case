package com.jxau.bank.view;

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Welcome extends JFrame implements ActionListener {

    JButton jb1, jb2;  //按钮
    JPanel jp0, jp1, jp2, jp3, jp4;        //面板
    JTextField jtf;   //文本框
    JLabel jlb0, jlb1, jlb2; //标签
    JPasswordField jpf; //密码框
    JRadioButton jrb1, jrb2;
    ButtonGroup bg;

    private ManagerInterface manager = ManagerImpl.getInstance();


    public void Welcome() {
        jb1 = new JButton("登录");
        jb1.setFont(new Font("Microsoft JhengHei",1,16));
        jb2 = new JButton("注册");
        jb2.setFont(new Font("Microsoft JhengHei",1,16));
        //设置按钮监听
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        //jb1.setBounds(100,300,80,30);

        //创建面板
        jp0 = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        //添加标签
        jlb0 = new JLabel("银 行 系 统 1.9");
        jlb0.setFont(new Font("微软雅黑",1,32));
        jlb1 = new JLabel("用户名：");
        jlb1.setFont(new Font("Microsoft JhengHei",0,22));
        jlb2 = new JLabel("密  码：");
        jlb2.setFont(new Font("Microsoft JhengHei",0,22));

        //创建文本框和密码框
        jtf = new JTextField(10);
        jtf.setFont(new Font("Microsoft JhengHei",1,16));
        jpf = new JPasswordField(10);
        jpf.setFont(new Font("Microsoft JhengHei",1,16));

        //创建单选框
        jrb1 = new JRadioButton("管理员");
        jrb1.setFont(new Font("Microsoft JhengHei",1,16));
        jrb2 = new JRadioButton("用 户");
        jrb2.setFont(new Font("Microsoft JhengHei",1,16));
        //设置用户单选框为默认选中
        jrb2.setSelected(true);

        //使单选框互斥
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        //加入面板中
        jp0.add(jlb0);
        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);

        jp3.add(jrb1);
        jp3.add(jrb2);

        jp4.add(jb1);
        jp4.add(jb2);

        //将JPane加入JFrame中
        this.add(jp0);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);

        //设置布局
        this.setTitle("银行系统");
        this.setLayout(new GridLayout(5, 1));
        this.setSize(700, 500);   //设置窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口

        this.setVisible(true);  //设置可见
        this.setResizable(false);   //设置不可拉伸大小

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = jtf.getText();
        String password = jpf.getText();
        if (e.getActionCommand() == "登录") {
            if (jrb1.isSelected()){
                //管理员登录
                if("admin".equals(username) && "admin".equals(password)) {
                    new AdminMenu().AdminMenu();
                    dispose();
                }else if("".equals(username) || "".equals(password)){
                    JOptionPane.showMessageDialog(null,"用户名或密码不能为空");
                }else{
                    JOptionPane.showMessageDialog(null, "您不是管理员！");
                }
            }else {
                //用户登录
                if("".equals(username) || "".equals(password)){
                    JOptionPane.showMessageDialog(null,"用户名或密码不能为空");
                } else{
                    checkLogin(username, password);
                }
            }


        } else if (e.getActionCommand() == "注册") {
            if (jrb1.isSelected()){
                JOptionPane.showMessageDialog(null,"请选择普通用户进行注册！");
            }else {
                checkRegister(username, password);
            }
        }
    }

    public void checkLogin(String loginName,String loginPassword){
        boolean flag = false;
        try {
            flag = manager.login(loginName, loginPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "用户名或密码错误！请重试","消息提示",JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "登录成功！","消息提示",JOptionPane.WARNING_MESSAGE);
            dispose();  //使文原窗体消失
            new Menu().Menu(loginName,loginPassword);
        }

    }

    public void checkRegister(String username,String password){
        if ("".equals(username) && "".equals(password)){
            JOptionPane.showMessageDialog(null, "用户名或密码不能为空！","消息提示",JOptionPane.WARNING_MESSAGE);
        }else {
            boolean registerFlag = false;
            try {
                registerFlag = manager.register(username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (registerFlag){
                JOptionPane.showMessageDialog(null, "注册成功！","消息提示",JOptionPane.WARNING_MESSAGE);
                //dispose();
                //new Menu().Menu(username,password);
            }else {
                JOptionPane.showMessageDialog(null, "该用户已存在！","消息提示",JOptionPane.WARNING_MESSAGE);

            }
        }

    }



}
