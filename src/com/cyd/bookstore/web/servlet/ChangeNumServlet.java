package com.cyd.bookstore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.service.ProductService;

@WebServlet("/changeNum")
public class ChangeNumServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取参数
		String id = req.getParameter("id");//得到商品id
		String num = req.getParameter("num");//购物数量
		
		//2.更新购物车数据
		//2.1通过id查找商品
		ProductService ps = new ProductService();
		Product p = ps.findBook(id);
		
		//2.2通过商品更新session数据
		@SuppressWarnings("unchecked")//忽略警告
		Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
	
		//替换
		if(cart.containsKey(p)) {//判断是否有该商品
			if("0".equals(num)){//如果没有商品则移除
				cart.remove(p);
			}
			else {
				cart.put(p, Integer.parseInt(num));
			}
		}
		
		//重新保存到session
		req.getSession().setAttribute("cart", cart);
		
		//回到购物车页面
		resp.sendRedirect(req.getContextPath() + "/cart.jsp");
	}
}
