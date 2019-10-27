package com.jxau.service.impl;

import com.jxau.dao.ProductDao;
import com.jxau.dao.impl.ProductDaoImpl;
import com.jxau.domain.PageBean;
import com.jxau.domain.Product;
import com.jxau.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 20:03
 * @Version 1.0
 */
public class ProductServiceImpl implements ProductService {
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findHotProduct() throws SQLException {
        return productDao.findHotProduct();
    }

    @Override
    public List<Product> findNewProduct() throws SQLException {
        return productDao.findNewProduct();
    }

    @Override
    public PageBean findCategoryById(String cid, int currentPage, int currentCount) throws SQLException {
        int totalCount = productDao.getTotalCount(cid);
        int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
        //开始查询的索引 = （当前页码 - 1） * 每页显示的条数
        int index = (currentPage - 1) * currentCount;
        //当前页显示的数据
        List<Product> products = productDao.findProductByPage(Integer.parseInt(cid), index, currentCount);

        //封装一个PageBean返回web层
        PageBean<Product> pageBean = new PageBean<Product>();
        //封装当前页
        pageBean.setCurrentPage(currentPage);
        //封装每页显示的条数
        pageBean.setCurrentCount(currentCount);
        //封装总记录数
        pageBean.setTotalCount(totalCount);
        //封装总页数
        pageBean.setTotalPage(totalPage);
        //封装查询出来的数据
        pageBean.setList(products);

        return pageBean;

    }

    @Override
    public Product findProductById(String pid) throws SQLException {
        return productDao.findProductById(pid);
    }
}
