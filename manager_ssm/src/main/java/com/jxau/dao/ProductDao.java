package com.jxau.dao;

import com.jxau.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品持久化接口
 */
public interface ProductDao {
    //查询所有产品信息
    @Select("select * from product")
    List<Product> findAll() throws Exception;
}
