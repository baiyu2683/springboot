package com.trs.util;

import java.util.List;

public interface PagedList<T> extends List<T> {
	/**
	 * 获得记录集的总记录数。
	 * @return
	 */
	long getTotal();
	/**
	 * 获得当前页第一条记录的偏移量。
	 * @return
	 */
	long getOffset();
	/**
	 * 获得页面大小。
	 * @return
	 */
	int getLimit();
}
