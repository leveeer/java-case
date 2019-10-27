package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        //从redis中查询
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0){
            //如果为空，需要从数据库查询，再将数据存入redis
            //从数据库查询
            cs = dao.findAll();
            //将集合数据存入redis中的category的key
            for (int i = 0; i < cs.size() ; i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            //如果不为空,将set集合中的数据存入list集合，使数据结构统一
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
