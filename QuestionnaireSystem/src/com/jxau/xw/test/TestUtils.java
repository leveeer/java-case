package com.jxau.xw.test;

import com.jxau.xw.utils.JDBCUtils;
import org.junit.Test;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 测试类
 */
public class TestUtils {

    @Test
    public void testJDBCUtils(){
        DataSource ds = JDBCUtils.getDataSource();
        try {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
