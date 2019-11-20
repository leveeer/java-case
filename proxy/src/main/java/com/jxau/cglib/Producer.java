package com.jxau.cglib;

public class Producer {

    /**
     * 销售
     */
    public void saleProduct(float money){
        System.out.println("销售商品，并拿到钱：" + money );
    }

    /**
     * 售后
     * @param money
     */
    public void afterService(float money){
            System.out.println("提供售后服务，并拿到钱：" + money);
        }

}
