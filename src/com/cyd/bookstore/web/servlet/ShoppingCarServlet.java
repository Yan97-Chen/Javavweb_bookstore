package com.cyd.bookstore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.service.ProductService;
/*
 * 难点：判断商品是否已经存在在购物车
 * 解决方法：在product里面更具id，增加equals方法
 */

@WebServlet("/addCart")
public class ShoppingCarServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.获取id
		String id = request.getParameter("id");
		
		//2.通过id查询数据库对应商品dao/service
		ProductService ps = new ProductService();
		Product p = ps.findBook(id);
		
		//3.把购物商品放到购物车Map
			//3.1先从session获取购物车数据【cart】
		Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		
		//3.2如果没有购物车数据，就创建一个map对象
		if(cart == null){
			cart = new HashMap<Product, Integer>();
			cart.put(p, 1);
		}
		else {
			
			//3.3判断map里面是否当前想购物商品
			if(cart.containsKey(p)) {
				cart.put(p, cart.get(p)+1);
			}
			else {
				cart.put(p, 1);
			}
		}
		
		//4.打印购物车数据
		for(Entry<Product, Integer> entry : cart.entrySet()) {
			System.out.println(entry.getKey() + "数量" + entry.getValue());
		}
		
		//5.存session
		request.getSession().setAttribute("cart",cart);
		
		//6.响应客户端
		String a1 = "<a href=\"" + request.getContextPath()+"/showProductByPage\">继续购物</a>";
		String a2 = "&nbsp;&nbsp;<a href=\"" + request.getContextPath()+"/cart.jsp\">查看购物车</a>";

		response.getWriter().write(a1);
		response.getWriter().write(a2);

	}
	
//	//测试
//	public static void main(String[] args) {
//		Map<Product, Integer> car = new HashMap<Product, Integer>();
//		
//		ProductService ps = new ProductService();
//		Product p1 = ps.findBook("2");
//		car.put(p1, 1);
//		
//		Product p2 = ps.findBook("2");
//		if(car.containsKey(p2)) {
//			System.out.println("p2已经存在购物");
//		}
//		else {
//			System.out.println("p2不存在购物车");
//		}
//	}

}
