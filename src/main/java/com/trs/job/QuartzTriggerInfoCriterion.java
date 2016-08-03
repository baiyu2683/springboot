package com.trs.job;


/**
 * 封装对QuartzTriggerInfo对象的查询条件.
 * @see QuartzTriggerInfo
 */
public class QuartzTriggerInfoCriterion{

	/** 关键词. */
	private String keyword;

	/** 分组名. */
	private String group;

	/** 执行情况：0-不限制、1-执行失败、2-正在执行、3-等待执行 */
	private Integer stateType;

	/** 任务耗时：0-不限制、1-1秒以下、2-1秒到10秒、3-10秒到30秒、4-30秒到1分钟、5-1分钟以上 */
	private Integer costType;

	/** 健康度： 0-不限制、1-20%以下、2-20%到40%、3-40%到60%、4-60%到80%、5-80%以上 */
	private Integer healthType;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getStateType() {
		return stateType;
	}

	public void setStateType(Integer stateType) {
		this.stateType = stateType;
	}

	public Integer getCostType() {
		return costType;
	}

	public void setCostType(Integer costType) {
		this.costType = costType;
	}

	public Integer getHealthType() {
		return healthType;
	}

	public void setHealthType(Integer healthType) {
		this.healthType = healthType;
	}

}
