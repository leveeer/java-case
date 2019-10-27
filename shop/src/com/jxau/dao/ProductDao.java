package com.jxau.dao;

import com.jxau.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 20:16
 * @Version 1.0
 */
public interface ProductDao {
    List<Product> findHotProduct() throws SQLException;

    List<Product> findNewProduct() throws SQLException;

    int getTotalCount(String cid) throws SQLException;

    List<Product> findProductByPage(int cid, int index, int currentCount) throws SQLException;

    Product findProductById(String pid) throws SQLException;
}
