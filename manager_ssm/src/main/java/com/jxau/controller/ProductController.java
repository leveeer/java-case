package com.jxau.controller;

import com.jxau.domain.Product;
import com.jxau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
        mv.addObject("productList",products);
        mv.setViewName("product-list");
        return mv;
    }
}
