 package com.cyd.bookstore.service;

import java.sql.SQLException;

import com.cyd.bookstore.dao.UserDao;
import com.cyd.bookstore.exception.UserException;
import com.cyd.bookstore.model.User;
import com.cyd.bookstore.utils.SendJMail;

public class UserService {

	UserDao userDao = new UserDao();
	public void register(User user) throws UserException {
		try {
			userDao.addUser(user);
			//项目发布时，连接更改为域名www.bookstore.com

			String link = "http://127.0.0.1:8080/bookstore/active?activeCode=" +user.getActiveCode();
			String html = "<a href=\"" + link + "\">激活码连接</a>";

			System.out.println(html);
			//发送激活邮件
			SendJMail.sendMail(user.getEmail(), html);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("用户注册失败，用户名重复");
		}
	}
	
	/*
	 * 激活用户
	 * 不要再try里面写throw，否则catch会自动捕获
	 */
	public void activeUser(String activeCode) throws UserException {
		
		User user = null;
		
		try {
			//1.查询激活码的用户是否存在
			user = userDao.findUserByActiveCode(activeCode);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UserException("激活失败");
		}
		
		if(user == null) {
			throw new UserException("非法激活，用户不存在");
		}
		
		if(user!=null && user.getState() == 1) {
			throw new UserException("用户已经激活");
		}
		
		//2.激活用户
		try {
			userDao.updateState(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new UserException("激活失败");
		}
	}
	
	public User login(String username, String password) throws UserException {
		
		try {
			//1.检查
			User user = userDao.findUserByUsernameAndPassword(username, password);
			
			//2.判断
			if(user == null) {
				throw new UserException("用户名或者密码错误！");
			}
			
			if(user.getState() == 0) {
				throw new UserException("用户未激活，请先激活用户！");
			}
			
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("登录失败");
		}
	}
	
	public User findUserById(String id) throws UserException {
		
		try {
			User user = userDao.findUserById(id);
			
			if(user == null) {
				throw new UserException("用户不存在！");
			}
			
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("未知错误！");
		}
		
	}
	
	public void modifyUserInfo(User user) throws UserException {
		
		try {
			userDao.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-gene rated catch block
			e.printStackTrace();
			throw new UserException("未知错误！");
		}
		
	}
}
