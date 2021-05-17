package com.cyd.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.cyd.bookstore.exception.UserException;
import com.cyd.bookstore.model.User;
import com.cyd.bookstore.service.UserService;


@WebServlet("/modifyUserInfo")
public class ModifyUserInfoServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		//1.获取表单参数
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println(user);
			
			//2.修改数据库信息
			UserService us = new UserService();
			us.modifyUserInfo(user);
			
			//3.跳转页面
			response.sendRedirect(request.getContextPath() + "/modifyUserInfoSuccess.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
