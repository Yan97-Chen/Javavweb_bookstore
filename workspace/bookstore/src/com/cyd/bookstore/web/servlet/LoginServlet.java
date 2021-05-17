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


@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserService us = new UserService();
		
		try {
			User user = us.login(username, password);
			//把user保存到session对象
			//登陆成功，放回index.jsp
			request.getSession().setAttribute("user", user);
			
			if("管理员".equals(user.getRole())) {
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");

			}
			else {
				response.sendRedirect(request.getContextPath()+"/index.jsp");

			}
//			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//登陆失败，返回登陆界面
			
			request.setAttribute("login_msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
