package com.cyd.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.utils.C3P0Utils;

public class ProductDao {

	public long count(String category) throws SQLException {
		
		long count = 0;
		
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		String sql = "select count(*) from products where 1=1";
		
		if (category != null && !"".equals(category)) {
			sql += " and category = ?";
			count = (long) qr.query(sql, new ScalarHandler(),category);
		}
		else {
			count = (long) qr.query(sql, new ScalarHandler());

		}
		return count;
		
	}
	
	public List<Product> findBooks(String category, int page, int pageSize) throws SQLException{
		
		String sql = "select * from products where 1=1";
		
		List<Object> params = new ArrayList<Object>();
		if(category != null && !"".equals(category)) {
			sql += " and category = ?";
			params.add(category);
		}
		
		sql +=" limit ?,?";

		int start = (page - 1) * pageSize;
		int length = pageSize;
		
		params.add(start);
		params.add(length);
		
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class),params.toArray());
		
	}
	
	public Product findBook(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id = ?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}
	
	public static void main(String[] args) throws SQLException {
		ProductDao dao = new ProductDao();
		long count = dao.count(null);
		System.out.println(count);
//		String category = "计算机";
//		long count = dao.count(category);
//		
//		List<Product> books = dao.findBooks(category, 1, 4);
//		for(Product b : books) {
//			System.out.println(b);
		}
	}


