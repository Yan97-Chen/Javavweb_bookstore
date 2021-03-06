package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.model.User;

@WebServlet("/settleAccount")
public class SettleAccountServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//判断当前浏览器是否有登陆
		User user = (User) req.getSession().getAttribute("user");
		//如果有登陆进行订单页面
		if(user != null) {
			resp.sendRedirect(req.getContextPath() + "/order.jsp");
		}
		else {
			//如果没有登陆，先进行登陆
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
}
