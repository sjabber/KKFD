package com.kkfd.dto;

import java.util.List;

public class PageDTO<T> {// 마이프로젝트, 펀딩한프로젝트
	private int currentPage = 1;
	private int totalPage;
	private static final int CNT_PER_PAGE = 10; //페이지별 보여줄 목록수
	private List<T> list; 
	private static final int CNT_PER_PAGE_GROUP = 10;//페이지그룹의 페이지수
	private int startPage = 1;
	private int endPage;
	private String url;  //찾아보기
	public PageDTO() {}
	public PageDTO(int currentPage, int totalPage, List<T> list,String url) {
		this.currentPage=currentPage;
		this.totalPage=totalPage;
		this.list=list;
		int startPage = (currentPage - 1) / CNT_PER_PAGE_GROUP * CNT_PER_PAGE_GROUP + 1;
		int endPage = startPage + CNT_PER_PAGE_GROUP - 1;
		if(endPage > totalPage) endPage = totalPage;
		this.startPage=startPage;
		this.endPage=endPage;
		//int endPage = (int)(Math.ceil(currentPage/(double)CNT_PER_PAGE_GROUP))*CNT_PER_PAGE_GROUP;	
		//this.startPage = endPage-CNT_PER_PAGE_GROUP+1;
		//this.endPage = (totalPage-startPage<CNT_PER_PAGE_GROUP)? totalPage : endPage; 
		}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static int getCntPerPage() {
		return CNT_PER_PAGE;
	}
	public static int getCntPerPageGroup() {
		return CNT_PER_PAGE_GROUP;
	}
	@Override
	public String toString() {
		return "PageDTO [currentPage=" + currentPage + ", totalPage=" + totalPage + ", list=" + list + ", startPage="
				+ startPage + ", endPage=" + endPage + ", url=" + url + "]";
	}

	
}
