package com.jxau.service;

import com.jxau.domain.PageBean;
import com.jxau.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 20:03
 * @Version 1.0
 */
public interface ProductService {
    List<Product> findHotProduct() throws SQLException;

    List<Product> findNewProduct() throws SQLException;

    PageBean findCategoryById(String cid, int currentPage, int currentCount) throws SQLException;

    Product findProductById(String pid) throws SQLException;
}
