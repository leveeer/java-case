package com.jxau.service.impl;

import com.jxau.dao.ProductDao;
import com.jxau.domain.Product;
import com.jxau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {

        return productDao.findAll();
    }
}
