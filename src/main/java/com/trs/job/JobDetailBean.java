package com.trs.job;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

public class JobDetailBean extends JobDetailImpl implements BeanNameAware,
		InitializingBean {

	private static final long serialVersionUID = -3360958397201015170L;

	private String beanName;

	public void setJobDataMap(Map jobDataAsMap) {
		getJobDataMap().putAll(jobDataAsMap);
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public void afterPropertiesSet() {
		JobKey key = getKey();
		if (getKey().getName() == null) {
			setName(this.beanName);
		}
		if (getGroup() == null) {
			setGroup(Scheduler.DEFAULT_GROUP);
		}
	}

	@Override
	public JobKey getKey() {
		return super.getKey();
	}

	@Override
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public Class<? extends Job> getJobClass() {
		return super.getJobClass();
	}

	@Override
	public JobDataMap getJobDataMap() {
		return super.getJobDataMap();
	}

	@Override
	public boolean isDurable() {
		return false;
	}

	@Override
	public boolean isPersistJobDataAfterExecution() {
		return false;
	}

	@Override
	public boolean isConcurrentExectionDisallowed() {
		return false;
	}

	@Override
	public boolean requestsRecovery() {
		return false;
	}

	@Override
	public Object clone() {
		return null;
	}

	@Override
	public JobBuilder getJobBuilder() {
		return super.getJobBuilder();
	}
}
