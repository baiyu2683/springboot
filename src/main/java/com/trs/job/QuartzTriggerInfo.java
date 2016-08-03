package com.trs.job;

import org.apache.commons.lang.StringUtils;
import org.quartz.Trigger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Quartz触发器的信息.
 * @see Trigger
 */
@Entity
@Table(name = "quartz_trigger_info")
public class QuartzTriggerInfo implements Serializable{

	private static final long serialVersionUID = 769652331649937662L;
	@Id
	private String name;
	@Id
	private String group;

	private String jobName;

	private String jobGroup;

	private String description;

	private Long nextFireTime;

	private transient Date nextFireTime2;

	private Long prevFireTime;

	private transient Date prevFireTime2;

	private Integer priority;

	private String state;

	private Long startTime;

	private transient Date startTime2;

	private Long endTime;

	private transient Date endTime2;

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

	/** 最近10次运行的结果，用一个包含10个字符的字符串来表示，1表示成功，0表示失败，最近的运行结果保存在字符串右侧，然后依次往左存放。 */
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
		else if(obj instanceof QuartzTriggerInfo){
			QuartzTriggerInfo other=(QuartzTriggerInfo) obj;
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Long getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
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

	public Date getNextFireTime2() {
		return nextFireTime2;
	}

	public void setNextFireTime2(Date nextFireTime2) {
		this.nextFireTime2 = nextFireTime2;
	}

	public Date getPrevFireTime2() {
		return prevFireTime2;
	}

	public void setPrevFireTime2(Date prevFireTime2) {
		this.prevFireTime2 = prevFireTime2;
	}

	public Date getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(Date startTime2) {
		this.startTime2 = startTime2;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
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

	public Integer getHealthDegree() {
		return healthDegree;
	}

	public void setHealthDegree(Integer healthDegree) {
		this.healthDegree = healthDegree;
	}
}
