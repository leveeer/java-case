package com.jxau.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    @Test
    public void testConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem_ssm", "root", "root");
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinal(){
        final int a;
        final int b = 4;
        final int c;
        a = 2;
        System.out.println("a=" + a);
        System.out.println("b=" + b);

    }


    @Test
    public void testFinally(){
        int i = testInt();
        System.out.println(i);
    }

    public int testInt(){
        try {
            return 0;
        }catch (Exception e){
            return 1;
        }finally {
            return 2;
        }
    }

    /**
     * 测试jedis是否连接成功
     */
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("116.62.64.37", 6379);
        jedis.auth("password");
        String ping = jedis.ping();
        System.out.println(ping);
    }
}
