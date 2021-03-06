package com.cyd.bookstore.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")//拦截请求路径
public class MyEncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//解决响应乱码
		HttpServletResponse mResponse = (HttpServletResponse) response;
		mResponse.setHeader("content-type", "text/html;charset=utf-8");//不属于response， 强转
		
		//1.设置POST请求中文乱码的问题
	
		HttpServletRequest hsr = (HttpServletRequest)request;
		if(hsr.getMethod().equalsIgnoreCase("post")){
			request.setCharacterEncoding("UTF-8");

		}
		chain.doFilter(request, response);

	
		
	}

}
