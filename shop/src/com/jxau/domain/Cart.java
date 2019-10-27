package com.jxau.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Cart
 * @Description TODO
 * @Author xie
 * @Date 2019/8/25 00:18
 * @Version 1.0
 */
public class Cart implements Serializable {

    //购物车中存储的购物项，使用map的原因：给每一项定义一个唯一标识，方便增加和删除购物项
    private Map<String, CartItem> cartItems = new HashMap<String, CartItem>();

    //购物车商品金额总计
    private double total;

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
