package com.jxau.bank.view;

import com.jxau.bank.manager.ManagerInterface;
import com.jxau.bank.manager.impl.ManagerImpl;
import com.jxau.bank.model.OperateBean;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class RecordFrame extends JFrame {

    private ManagerInterface manager = ManagerImpl.getInstance();
    JTable jt = null;
    JScrollPane jsp = null;

    public void recordFrame(String username) {
        //columnNames存放列名
        Vector<String> columnNames = new Vector();
        //设置列名
        columnNames.add("用户id");
        columnNames.add("用户名");
        columnNames.add("操作类型");
        columnNames.add("操作时间");
        columnNames.add("操作金额");
        columnNames.add("上次余额");
        columnNames.add("本次余额");

        //rowData用来存放行数据
        //rowData可以存放多行,开始从数据库里取
        Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();

        Vector<OperateBean> record = manager.findRecordByUsername(username);

        for (int i = 0; i < record.size(); i++) {
            Vector<Object> v = new Vector<>();
            OperateBean o = record.get(i);
            v.add(o.getId());
            v.add(o.getOusername());
            v.add(o.getOtype());
            v.add(o.getOtime());
            v.add(o.getOmoney());
            v.add(o.getOBmoney());
            v.add(o.getAmoney());
            rowData.add(v);
        }


        //初始化JTable
        DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
        jt = new JTable(dtm){
            //设置表格为不可编辑
             @Override
             public boolean isCellEditable(int row, int col){
                 return false;
             }
        };

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        //设置表格文字居中
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        //初始化 jsp
        jsp = new JScrollPane(jt);

        //把jsp放入到jframe
        this.add(jsp);

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
        this.setTitle("交易记录");

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //设置仅关闭当前窗口
        this.setVisible(true);

    }
}
