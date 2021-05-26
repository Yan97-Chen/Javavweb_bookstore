package com.cyd.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.cyd.bookstore.dao.OrderDao;
import com.cyd.bookstore.dao.OrderItemDao;
import com.cyd.bookstore.dao.ProductDao;
import com.cyd.bookstore.model.Order;
import com.cyd.bookstore.model.OrderItem;
import com.cyd.bookstore.utils.ManagerThreadLocal;

public class OrderService {

//	public void createOrder(Order order, List<OrderItem> items) {
//		
//	}
	/*/
	 * 添加订单业务方法
	 */
	
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private ProductDao productDao = new ProductDao();
	public void createOrder(Order order) {
		try {
			//开启事务
			ManagerThreadLocal.beginTransaction();
			//插入订单表
			orderDao.add(order);
			
			//插入订单详情表
			orderItemDao.addOrderItems(order.getItems());
			
			//减库存
			for(OrderItem item : order.getItems()) {
				productDao.updatePNum(item.getProduct().getId(), item.getBuynum());
			}
			
			//结束事物
			ManagerThreadLocal.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			//事务回滚
			ManagerThreadLocal.rollback();
		}
	}
}
