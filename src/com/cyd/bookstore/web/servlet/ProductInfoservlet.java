package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.dao.ProductDao;
import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.service.ProductService;
import com.sun.net.httpserver.HttpServer;

@WebServlet("/productInfo")
public class ProductInfoservlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取id
		String id = request.getParameter("id");
		
		//2.通过id查询物品对应商品dao/service
		ProductService ps = new ProductService();
		Product product = ps.findBook(id);
		
		//3.把商品存在request，跳转页面到product_info.jsp
		request.setAttribute("product", product);
		request.getRequestDispatcher("product_info.jsp").forward(request, response);
	}

}
