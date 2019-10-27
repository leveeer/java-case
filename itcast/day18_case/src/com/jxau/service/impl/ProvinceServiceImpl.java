package com.jxau.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxau.dao.ProvinceDao;
import com.jxau.dao.impl.ProvinceDaoImpl;
import com.jxau.domain.Province;
import com.jxau.service.ProvinceService;
import com.jxau.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    //声明dao
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用redis缓存
     * @return
     */
    @Override
    public String findAllJson() {
        //先从redis中查询数据
        //获取redis客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");

        //判断province_json数据是否为null
        if(province_json == null || province_json.length() == 0){
            //Redis中没有数据
            //从数据库中查询
            List<Province> provinces = dao.findAll();
            //将list序列化为json
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(provinces);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json存入redis中
            jedis.set("province",province_json);
            //归还连接
            jedis.close();
        }
        return province_json;
    }
}