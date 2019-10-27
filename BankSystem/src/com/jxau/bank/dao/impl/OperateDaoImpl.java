package com.jxau.bank.dao.impl;

import com.jxau.bank.dao.OperateDaoInterface;
import com.jxau.bank.model.OperateBean;
import com.jxau.bank.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class OperateDaoImpl implements OperateDaoInterface {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    /**
     * 添加记录
     * @param operateBean
     */
    @Override
    public void insertRecord(OperateBean operateBean) {
        //System.out.println(operateBean.getOusername());
        conn = JDBCUtils.getConnection();
        String sql = "insert into operate values(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,operateBean.getId());
            ps.setString(2,operateBean.getOusername());
            ps.setString(3,operateBean.getOtype());
            ps.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));
            ps.setDouble(5,operateBean.getOmoney());
            ps.setDouble(6,operateBean.getOBmoney());
            ps.setDouble(7,operateBean.getAmoney());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null,ps,conn);
        }

    }

    /**
     * 根据用户名查找记录
     * @param username
     * @return
     */
    @Override
    public Vector<OperateBean> findRecordByUsername(String username) {
        Vector<OperateBean> vector = null;
        conn = JDBCUtils.getConnection();
        String sql = "select * from operate where Ousername = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();

            vector = new Vector<>();
            OperateBean record = null;
            while (rs.next()){
                record = new OperateBean();
                record.setId(rs.getInt("id"));
                record.setOusername(rs.getString("Ousername"));
                record.setOtype(rs.getString("Otype"));
                record.setOtime(rs.getTimestamp("Otime"));
                record.setOmoney(rs.getDouble("Omoney"));
                record.setOBmoney(rs.getDouble("OBmoney"));
                record.setAmoney(rs.getDouble("OAmoney"));
                vector.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,ps,conn);
        }
        return vector;
    }

}
