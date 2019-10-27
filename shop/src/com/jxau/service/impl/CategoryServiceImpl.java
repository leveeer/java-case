package com.jxau.service.impl;

import com.jxau.dao.CategoryDao;
import com.jxau.dao.impl.CategoryDaoImpl;
import com.jxau.domain.Category;
import com.jxau.domain.PageBean;
import com.jxau.domain.Product;
import com.jxau.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 21:38
 * @Version 1.0
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAllCategory() throws SQLException {
        return categoryDao.findAllCategory() ;
    }

    @Override
    public Category findCategoryById(String cid) throws SQLException {

        return categoryDao.findCategoryById(cid);
    }


}
