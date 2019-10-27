package com.jxau.bank.test;

import com.jxau.bank.util.JDBCUtils;
import com.jxau.bank.view.Welcome;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestBank {
    public static void main(String[] args) {
        new Welcome().Welcome();
    }

    @Test
    public void testc3p0() {
        DataSource ds = JDBCUtils.getDataSource();
        try {
            Connection conn = ds.getConnection();
            System.out.println(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
