package com.trs.job;

import org.apache.commons.lang.StringUtils;
import org.quartz.Trigger;

import java.io.Serializable;
import java.util.Date;

/**
 * Quartz触发器的运行统计信息.
 * @see Trigger
 */
public class QuartzTriggerExtraState implements Serializable {

	private static final long serialVersionUID = 2961456272685770370L;

	/** 在lastTenResult字段中记录最近几次的运行结果，最小不能低于5次（健康度分为5个档） */
	public static final int LAST_NCOUNT=5;

	/** 触发器的名称. */
	private String name;

	/** 触发器的分组名称. */
	private String group;

	/** 上一次运行是否成功. */
	private Boolean lastRunResult;

	/** 最后一次运行的耗时时间. */
	private Long lastRunCost;

	/** 最后一次成功的时间. */
	private Date lastSuccessTime;

	/** 最后一次失败的时间. */
	private Date lastFailTime;

	/** 运行总数. */
	private Integer totalRunCount=0;

	/** 成功总数. */
	private Integer totalSuccessCount=0;

	/** 失败总数. */
	private Integer totalFailCount=0;

	/** 最近N次（由{@link #LAST_NCOUNT}常量指定）运行的结果，用一个包含N个字符的字符串来表示，1表示成功，0表示失败，最近的运行结果保存在字符串右侧，然后依次往左存放。 */
	private String lastTenResult = "";

	/** lastTenResult中的成功率. */
	private Integer healthDegree=100;

	/** lastTenResult中的成功次数. */
	private Integer lastTenSuccessCount=0;

	/** lastTenResult中的失败次数. */
	private Integer lastTenFailCount=0;

	/** lastTenResult中的运行次数. */
	private Integer lastTenTotalCount=0;

	@Override
	public int hashCode() {
		return (this.group+"-"+this.name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		else if(obj instanceof QuartzTriggerExtraState){
			QuartzTriggerExtraState other=(QuartzTriggerExtraState) obj;
			return StringUtils.equals(this.group, other.group)&&StringUtils.equals(this.name, other.name);
		}else
			return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean getLastRunResult() {
		return lastRunResult;
	}

	public void setLastRunResult(Boolean lastRunResult) {
		this.lastRunResult = lastRunResult;
	}

	public Long getLastRunCost() {
		return lastRunCost;
	}

	public void setLastRunCost(Long lastRunCost) {
		this.lastRunCost = lastRunCost;
	}

	public Date getLastSuccessTime() {
		return lastSuccessTime;
	}

	public void setLastSuccessTime(Date lastSuccessTime) {
		this.lastSuccessTime = lastSuccessTime;
	}

	public Date getLastFailTime() {
		return lastFailTime;
	}

	public void setLastFailTime(Date lastFailTime) {
		this.lastFailTime = lastFailTime;
	}

	public Integer getTotalRunCount() {
		return totalRunCount;
	}

	public void setTotalRunCount(Integer totalRunCount) {
		this.totalRunCount = totalRunCount;
	}

	public Integer getTotalSuccessCount() {
		return totalSuccessCount;
	}

	public void setTotalSuccessCount(Integer totalSuccessCount) {
		this.totalSuccessCount = totalSuccessCount;
	}

	public Integer getTotalFailCount() {
		return totalFailCount;
	}

	public void setTotalFailCount(Integer totalFailCount) {
		this.totalFailCount = totalFailCount;
	}

	public String getLastTenResult() {
		return lastTenResult;
	}

	public void setLastTenResult(String lastTenResult) {
		this.lastTenResult = lastTenResult;
	}

	public Integer getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(Integer healthDegree) {
		this.healthDegree = healthDegree;
	}

	public Integer getLastTenSuccessCount() {
		return lastTenSuccessCount;
	}

	public void setLastTenSuccessCount(Integer lastTenSuccessCount) {
		this.lastTenSuccessCount = lastTenSuccessCount;
	}

	public Integer getLastTenFailCount() {
		return lastTenFailCount;
	}

	public void setLastTenFailCount(Integer lastTenFailCount) {
		this.lastTenFailCount = lastTenFailCount;
	}

	public Integer getLastTenTotalCount() {
		return lastTenTotalCount;
	}

	public void setLastTenTotalCount(Integer lastTenTotalCount) {
		this.lastTenTotalCount = lastTenTotalCount;
	}
}
