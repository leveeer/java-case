package com.jxau.bank.view;

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;
import com.jxau.bank.model.UserBean;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;


public class AdminMenu extends JFrame implements ActionListener {
    private ManagerInterface manager = ManagerImpl.getInstance();
    JTable jt = null;
    JScrollPane jsp = null;
    JPanel jp;
    JButton jb1, jb2;
    int id;
    int isBlocked;
    int row;

    public void AdminMenu() {
        //columnNames存放列名
        Vector<String> columnNames = new Vector();
        //设置列名
        columnNames.add("用户id");
        columnNames.add("用户名");
        columnNames.add("用户密码");
        columnNames.add("用户余额");
        columnNames.add("是否冻结");

        //rowData用来存放行数据
        //rowData可以存放多行,开始从数据库里取
        Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();

        List<UserBean> list = manager.findAll();

        for (int i = 0; i < list.size(); i++) {
            Vector<Object> v = new Vector<>();
            UserBean user = list.get(i);
            v.add(user.getId());
            v.add(user.getUserName());
            v.add(user.getPassword());
            v.add(user.getMoney());
            v.add(user.getIsBlocked());
            rowData.add(v);
        }

        //初始化JTable
        DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
        jt = new JTable(dtm) {
            //设置表格为不可编辑
            @Override
            public boolean isCellEditable(int row, int col) {
                    return false;
            }
        };


        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        //设置表格文字居中
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class, r);
        //初始化 jsp
        jsp = new JScrollPane(jt);
        this.setLayout(new BorderLayout());
        //把jsp放入到jframe
        this.add(jsp,BorderLayout.NORTH);

        jp = new JPanel();
        jb1 = new JButton("冻结");
        jb1.setFont(new Font("Microsoft JhengHei",1,22));
        jb2 = new JButton("解冻");
        jb2.setFont(new Font("Microsoft JhengHei",1,22));

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //单选
                row = jt.rowAtPoint(e.getPoint());// 选中行
                // int col =table.columnAtPoint(e.getPoint());//选中列
                //System.out.println(jt.getValueAt(row, 0) + "\t" + jt.getValueAt(row, 1));
                id = (int)jt.getValueAt(row,0);
                isBlocked = (int)jt.getValueAt(row,4);
                System.out.println(id + " " + isBlocked);
            }
        });

        jp.add(jb1);
        jp.add(jb2);
        this.add(jp,BorderLayout.SOUTH);

        this.setSize(1500, 600);

        jt.getTableHeader().setReorderingAllowed(false);   //不可整列移动
        //设置表头字体字号
        jt.getTableHeader().setFont(new Font("Microsoft JhengHei", 1, 20));
        //设置表格体字体字号
        jt.setFont(new Font("Microsoft JhengHei", 1, 16));
        jt.getTableHeader().setBackground(Color.LIGHT_GRAY);
        jt.setBackground(Color.cyan);
        //设置行高
        jt.setRowHeight(40);
        this.setTitle("用户信息");


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "冻结"){

            if (isBlocked == 1){
                JOptionPane.showMessageDialog(null,"该用户已经被冻结！无需重复操作");
            }else {
                isBlocked = 1;
                manager.blockedUserById(id,isBlocked);
                JOptionPane.showMessageDialog(null,"冻结成功！");
                jt.setValueAt(1,row,4);
            }
        }else if (e.getActionCommand() == "解冻"){
            if (isBlocked == 0){
                JOptionPane.showMessageDialog(null,"该用户未被冻结！无需此操作");
            }else {
                isBlocked = 0;
                manager.blockedUserById(id,isBlocked);
                JOptionPane.showMessageDialog(null,"解冻成功！");
                jt.setValueAt(0,row,4);
            }
        }
    }
}