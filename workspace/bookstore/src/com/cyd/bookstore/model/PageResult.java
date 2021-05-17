package com.cyd.bookstore.model;

import java.util.List;

public class PageResult<T> {
	List<T> list;
	long totalCount;//总条数
	int pageSize = 4;//每页显示条数
	int totlaPage;//总页数
	int currentPage;//当前页数
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotlaPage() {
		return totlaPage;
	}
	public void setTotlaPage(int totlaPage) {
		this.totlaPage = totlaPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "PageResult [totalCount=" + totalCount + ", pageSize=" + pageSize + ", totlaPage=" + totlaPage
				+ ", currentPage=" + currentPage + "]";
	}
	
	 

}
