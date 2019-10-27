package com.jxau.service;

import com.jxau.domain.Category;
import com.jxau.domain.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 21:37
 * @Version 1.0
 */
public interface CategoryService {
    List<Category> findAllCategory() throws SQLException;

    Category findCategoryById(String cid) throws SQLException;
}
