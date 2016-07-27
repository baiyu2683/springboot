package com.trs.util;

import com.trs.bean.OffsetLimit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PagedArrayList<T>{

	// fields ---------------------------------------------------------------
	private long offset;
	private int limit=10;
	private long pageNumber;
	private long totalPages;
	private long totalRecords;
	private List<T> pageData=new LinkedList<T>();

	// methods --------------------------------------------------------------
	public PagedArrayList() {
		super();
	}

	public PagedArrayList(List<T> pageData,long totalRecords,long offset,int limit) {
		super();
		this.offset=offset;
		this.limit = limit;
		this.totalRecords = totalRecords;
		this.pageData = pageData;
		if(limit>0){
			this.pageNumber = offset/limit+1;
			this.totalPages = (totalRecords+limit-1)/limit;
		}
	}

	public T get(int index){
		return pageData.get(index);
	}

	public static <T> PagedArrayList<T> pageList(List<T> allList,OffsetLimit offsetLimit){
		List<T> pageData=new ArrayList<T>();
		pageData.addAll(allList.subList((int)offsetLimit.getOffset(), (int)Math.min(offsetLimit.getOffset()+offsetLimit.getLimit(), allList.size())));
		return new PagedArrayList<T>(pageData,allList.size(),offsetLimit.getOffset(),offsetLimit.getLimit());
	}

	// accessors ------------------------------------------------------------
	public long getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public long getPageNumber() {
		return pageNumber;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public int getSize(){
		return pageData.size();
	}


}
