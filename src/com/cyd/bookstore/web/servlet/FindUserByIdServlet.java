package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.exception.UserException;
import com.cyd.bookstore.model.User;
import com.cyd.bookstore.service.UserService;

@WebServlet("/findUserById")
public class FindUserByIdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//1.获取参数
		String userid = request.getParameter("id");
		
		//2.从数据库查询
		UserService us = new UserService();
		try {
			User user = us.findUserById(userid);
			/*
			 * el表达式获取顺序：page,request,session,application
			 */
			//3.放在request
			request.setAttribute("u", user);
			
			//4.加 到modifyuserbyid，显示数据
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}

	}
}
