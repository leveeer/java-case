package com.jxau.dao;

import com.jxau.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 21:39
 * @Version 1.0
 */
public interface CategoryDao {
    List<Category> findAllCategory() throws SQLException;

    Category findCategoryById(String cid) throws SQLException;
}
