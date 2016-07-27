package com.trs.bean;

import java.io.Serializable;

/**
 * 通过指定记录的开始位置和记录条数，获取结果集中的一段连续记录.
 */
public class OffsetLimit implements Serializable {

	// fields ---------------------------------------------------------------
	private static final long serialVersionUID = 8605035284186919618L;

	public static final OffsetLimit ALL = new OffsetLimit(0, Integer.MAX_VALUE);

	public static final OffsetLimit DEFAULT = new OffsetLimit(0, 10);

	public static final OffsetLimit UNIQUE = new OffsetLimit(0, 1);

	/** 记录开始的位置，从0开始，默认为0. */
	private long offset;

	/** 记录条数，必须大于0，默认为10. */
	private int limit = 10;

	// methods --------------------------------------------------------------
	public OffsetLimit(long offset, int limit) {
		super();
		this.offset = offset;
		this.limit = limit;
	}

	// accessors ------------------------------------------------------------
	public long getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}
}
