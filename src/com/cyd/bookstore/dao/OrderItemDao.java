package com.cyd.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyd.bookstore.model.OrderItem;
import com.cyd.bookstore.utils.C3P0Utils;
import com.cyd.bookstore.utils.ManagerThreadLocal;

public class OrderItemDao {
	
	/*/
	 * 添加订单详情
	 */
//	public void addOrderItems(List<OrderItem> items) throws SQLException {
//		// TODO Auto-generated method stub
//		String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
//		
//		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
//		for(OrderItem item : items) {
//			qr.update(sql,item.getOrder().getId(),item.getProduct().getId(),item.getBuynum());
//		}
//	}

	/*/
	 * 提高性能，因为只执行了1条
	 */
	
	public void addOrderItems(List<OrderItem> items) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
		
		
		//创建二维数组
		Object[][] params = new Object[items.size()][];
		for(int i = 0; i < items.size(); i++) {
			OrderItem item = items.get(i);
			params[i] = new Object[] {item.getOrder().getId(),item.getProduct().getId(),item.getBuynum()
		};
	}
		//qr.batch(sql, params);
		QueryRunner qr = new QueryRunner();
		qr.batch(ManagerThreadLocal.getConnection(),sql, params);

}
}
