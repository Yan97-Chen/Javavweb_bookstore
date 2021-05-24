package com.cyd.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.cyd.bookstore.dao.ProductDao;
import com.cyd.bookstore.model.PageResult;
import com.cyd.bookstore.model.Product;

public class ProductService {

	ProductDao productdao = new ProductDao();
	
	public PageResult<Product> findBooks(String category, int page){
	
		try {
			//创建模型
			PageResult<Product> pr = new PageResult<Product>();

			//设置总记录数
			long totalCount = productdao.count(category);
			pr.setTotalCount(totalCount);
			
			//设置总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 /pr.getPageSize());
			pr.setTotlaPage(totalPage);
			
			//设置当前页数
			pr.setCurrentPage(page);
			
			//设置数据list
			List<Product> list = productdao.findBooks(category, page, pr.getPageSize());
			pr.setList(list);
			return pr;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Product findBook(String id) {
		try {
			return productdao.findBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
