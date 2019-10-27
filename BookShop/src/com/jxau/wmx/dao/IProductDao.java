package com.jxau.wmx.dao;

import java.util.List;
import java.util.Set;

import com.jxau.wmx.entity.Product;

/**
 * ��װ���ݿ�ı�Ĳ���
 * @author Administrator
 *
 */
public interface IProductDao {
	//������Ʒ
	int insert(Product product);
	//�޸���Ʒ
	int update(Product product);
	//ɾ����Ʒ
	int  deleteById(int pid);
    //��ѯ������Ʒ
	List<Product>findAll();
	//����Id���ط�����������Ʒ
	Product findById(int pid);
	//��ȡ�ܼ�¼��
	int findCount();
    //��ѯָ��ҳ�������
	List<Product> findByPage(int page, int pagesize) ;
	List<Product> getProductBySearchName(int page, int pagesize, String keywords);
	List<Product> findByBookName(String pname);
	List<Product> findByBookPrice(double price1, double price2);
	List<Product> findByBookCategory(String categoryId);
}
