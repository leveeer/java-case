package com.jxau.domain;

import java.io.Serializable;

/**
 * @ClassName CartItem
 * @Description TODO
 * @Author xie
 * @Date 2019/8/25 00:11
 * @Version 1.0
 */
public class CartItem implements Serializable {
    private Product product;
    private int buyNum;
    private double subtotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartItem{" + "product=" + product + ", buyNum=" + buyNum + ", subtotal=" + subtotal + '}';
    }
}
