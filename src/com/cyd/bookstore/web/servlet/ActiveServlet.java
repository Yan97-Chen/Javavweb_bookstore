package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.exception.UserException;
import com.cyd.bookstore.service.UserService;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setHeader("content-type", "text/html;charset=utf-8");
		String activecode = request.getParameter("activeCode");
		
		UserService us = new UserService();
		try {
			us.activeUser(activecode);
			response.getWriter().write("激活成功");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}

}
