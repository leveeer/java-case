package com.jxau.dao.impl;

import com.jxau.dao.CategoryDao;
import com.jxau.domain.Category;
import com.jxau.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CategoryDaoImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 21:39
 * @Version 1.0
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAllCategory() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category";
        List<Category> category = runner.query(sql, new BeanListHandler<Category>(Category.class));
        return category;
    }

    @Override
    public Category findCategoryById(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from category where cid = ?";
        Category category = runner.query(sql, new BeanHandler<Category>(Category.class), cid);
        return category;
    }
}
