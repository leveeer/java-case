package com.jxau.test;

import com.jxau.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName HibernateTest_c3p0
 * @Description hibernate测试c3p0连接池
 * @Author xie
 * @Date 2019/8/16 21:09
 * @Version 1.0
 */
public class HibernateTest_c3p0 {

    /**
     * @Author xie
     * @Description hibernate中如何使用原始的JDBC API
     *      JDBC的API
     *          Connection:
     *          Statement
     *          PreparedStatement
     *          ResultSet
     *
     * @Date  2019/8/16 21:12
     * @Param []
     * @return void
     **/

    @Test
    public void test01(){
        Session session = HibernateUtils.openSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                System.out.println(connection.getClass().getName());
            }
        });
    }
}
