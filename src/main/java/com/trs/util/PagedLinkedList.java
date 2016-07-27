package com.trs.util;

import java.util.LinkedList;


public class PagedLinkedList<T> extends LinkedList<T> implements PagedList<T> {

	private static final long serialVersionUID = -1874521308511748140L;
	private int limit;
	private long offset;
	private long total;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
