package com.jxau.service;

import com.jxau.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;

}
