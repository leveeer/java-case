package com.jxau.jedis.test;

import com.jxau.dao.ProvinceDao;
import com.jxau.dao.impl.ProvinceDaoImpl;
import com.jxau.domain.Province;
import com.jxau.jedis.util.JedisPoolUtils;
import com.jxau.service.ProvinceService;
import com.jxau.service.impl.ProvinceServiceImpl;
import com.jxau.util.JDBCUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis测试类
 */
public class JedisTest {
    @Test
    public void test1(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        jedis.set("username","zhangsan");
        //关闭连接
        jedis.close();
    }

    /**
     * String数据结构操作
     */
    @Test
    public void test2(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        //存储
        jedis.set("username","zhangsan");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储可以指定过期时间的key value
        jedis.setex("activecode",20,"hehe");//将activecode:hehe键值对存入redis,并且20秒后自动删除该键值对
        //关闭连接
        jedis.close();
    }

    /**
     * hash数据结构操作
     */
    @Test
    public void test3(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        //存储hash
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","female");
        //获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);
        //获取hash的所有map中的数据
        Map<String, String> user = jedis.hgetAll("user");

        //keyset遍历map
        Set<String> keySet = user.keySet();
        for (String key : keySet){
            //获取value
            String value = user.get(key);
            System.out.println(key + ":" + value);
        }

        //关闭连接
        jedis.close();
    }


    /**
     * list数据结构操作
     */
    @Test
    public void test4(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        //list 存储
        jedis.lpush("mylist","a","b","c");//从左边存
        jedis.rpush("mylist","a","b","c");//从右边存
        //list 范围获取
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist);

        //list弹出
        String element1 = jedis.lpop("mylist");
        System.out.println(element1);
        String element2 = jedis.rpop("mylist");
        System.out.println(element2);
        //关闭连接
        jedis.close();
    }

    /**
     * set数据结构操作
     */
    @Test
    public void test5(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        //set 存储
        jedis.sadd("myset","java","php","c++");
        //set 获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);
        //关闭连接
        jedis.close();
    }

    /**
     * sortedset数据结构操作
     */
    @Test
    public void test6(){
        //获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //操作
        //sortedset 存储
        jedis.zadd("mysortedset",3,"亚瑟");
        jedis.zadd("mysortedset",30,"李白");
        jedis.zadd("mysortedset",25,"韩信");

        //sortedset 获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);
        //关闭连接
        jedis.close();
    }

    /**
     * jedis连接池使用
     */
    @Test
    public void test7(){
        //创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);
        //创建jedis连接池对象
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);

        //获取连接
        Jedis jedis = jedisPool.getResource();
        //使用
        jedis.set("hehe","haha");
        String hehe = jedis.get("hehe");
        System.out.println(hehe);
        //关闭 归还到连接池中
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8() {
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //使用
        jedis.set("hello", "redis");
        String hello = jedis.get("hello");
        System.out.println(hello);
        //关闭 归还到连接池中
        jedis.close();
    }

    @Test
    public void testConnect() {
        ProvinceDao dao = new ProvinceDaoImpl();
        List<Province> list = dao.findAll();
        for (Province province : list) {
            System.out.println(list);

        }
    }

    @Test
    public void testService() {
        ProvinceService service = new ProvinceServiceImpl();
        List<Province> list = service.findAll();
        for (Province province : list) {
            System.out.println(province);

        }
    }

    @Test
    public void testJDBCUtils() {
        DataSource ds = JDBCUtils.getDataSource();
        try {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
