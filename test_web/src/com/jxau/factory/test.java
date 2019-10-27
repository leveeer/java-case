package com.jxau.factory;

/**
 * 测试工厂
 */
public class test {
    public static void main(String[] args) throws Exception {
        Student student = (Student) BeanFactory.getInstance().getBean("className");
        Student student2 = (Student) BeanFactory.getInstance().getBean("className");
        System.out.println(student);
        System.out.println(student2);
        System.out.println(student == student2);
    }
}
