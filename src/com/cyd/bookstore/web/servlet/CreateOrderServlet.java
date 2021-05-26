package com.cyd.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.cyd.bookstore.model.Order;
import com.cyd.bookstore.model.OrderItem;
import com.cyd.bookstore.model.Product;
import com.cyd.bookstore.model.User;
import com.cyd.bookstore.service.OrderService;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取session的user
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			resp.getWriter().write("非法访问");
			return;
		}
		
		Map<Product,Integer> cart = (Map<Product, Integer>) req.getSession().getAttribute("cart");
		if(cart == null || cart.size() == 0) {
			resp.getWriter().write("购物车没商品");
			return;
		}
		//1.封装数据
		Order order = new Order();
		try {
			//1.1把请求参数封装成order
			BeanUtils.populate(order, req.getParameterMap());
			
			//1.2补全order数据
			order.setId(UUID.randomUUID().toString());
			order.setOrdertime(new Date());
			order.setUser(user);
			
			//1.3封装订单详情orderitem[订单有n个商品]
			List<OrderItem> items = new ArrayList<OrderItem>();
			
			//取购物车
			double totalPrice = 0;
			  for(java.util.Map.Entry<Product, Integer> entry : cart.entrySet()) {
					OrderItem item = new OrderItem();
					//设置购物数量
					item.setBuynum(entry.getValue());
					//设置商品
					item.setProduct(entry.getKey());
					//设置订单
					item.setOrder(order);
					
					totalPrice += entry.getKey().getPrice() * entry.getValue();
					
					items.add(item);
			  }
			  
			  //设置order中的items
			  order.setItems(items);
			  
			  //1.4设置总价格
			  order.setMoney(totalPrice);
			  
			  //打印
			  System.out.println("-----------------");
			  System.out.println("订单：");
			  System.out.println(order);
			  
			  System.out.println("订单中商品：");
			  for(OrderItem item : items) {
				  System.out.println("商品名称：" + item.getProduct().getName() + "数量：" + item.getBuynum());
			  }
			  
			  //2.插入数据库
			  OrderService os = new OrderService();
			  os.createOrder(order);
			  
			  //3.订单成功（移除购物车数据）
			  req.getSession().removeAttribute("cart");
			  
			  //4.响应成功
			  resp.getWriter().write("下单成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
