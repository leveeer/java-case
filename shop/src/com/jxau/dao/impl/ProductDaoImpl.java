package com.jxau.dao.impl;

import com.jxau.dao.ProductDao;
import com.jxau.domain.Product;
import com.jxau.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ProductDaoImpl
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 20:16
 * @Version 1.0
 */
public class ProductDaoImpl implements ProductDao {
    QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    @Override
    public List<Product> findHotProduct() throws SQLException {
        String sql = "select * from product where is_hot = ? limit ?,?";
        List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class), 1, 0, 9);
        return list;
    }

    @Override
    public List<Product> findNewProduct() throws SQLException {
        String sql = "select * from product order by  pdate desc limit ?,?";
        List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class), 0, 9);
        return list;
    }

    @Override
    public int getTotalCount(String cid) throws SQLException {
        String sql = "select count(*) from product where cid = ?";
        Long result = (Long) runner.query(sql, new ScalarHandler(),cid);
        return result.intValue();
    }

    @Override
    public List<Product> findProductByPage(int cid, int index, int currentCount) throws SQLException {
        String sql = "select * from product where cid = ? limit ?,?";
        List<Product> products = runner.query(sql, new BeanListHandler<Product>(Product.class), cid, index, currentCount);
        return products;
    }

    @Override
    public Product findProductById(String pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
        return product;
    }
}
