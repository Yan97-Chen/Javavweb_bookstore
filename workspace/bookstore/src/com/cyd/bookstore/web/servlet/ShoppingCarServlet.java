package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.service.ProductService;

public class ShoppingCarServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取id
		String id = request.getParameter("id");
		
		//通过id查询数据库对应商品dao/service
		ProductService ps = new ProductService();
		Product p = ps.findBook(id);
	}

}
