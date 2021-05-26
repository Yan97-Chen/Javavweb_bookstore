package com.cyd.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyd.bookstore.model.Order;
import com.cyd.bookstore.utils.C3P0Utils;
import com.cyd.bookstore.utils.ManagerThreadLocal;

public class OrderDao {

	/*
	 * 添加订单
	 */
	
	public void add(Order order) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into orders ";
		sql += "(id,money,receiverAddress,receiverPhone,receiverName,paystate,odertime,user_id)";
		sql += " values(?,?,?,?,?,?,?,?)";		
		//2.参数
		List<Object> params = new ArrayList<Object>();
		params.add(order.getId());
		params.add(order.getMoney());
		params.add(order.getReceiverAddress());
		params.add(order.getReceiverName());
		params.add(order.getReceiverPhone());
		params.add(order.getPaystate());
		params.add(order.getOrdertime());
		params.add(order.getUser().getId());
		
//		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
//		qr.update(sql,params.toArray());

		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),sql,params.toArray());

	}
}
