package com.cyd.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyd.bookstore.model.User;

@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
	
		if(user != null) {
			//如果登陆进去myAccount.jsp
			response.sendRedirect("myAccount.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}
}
