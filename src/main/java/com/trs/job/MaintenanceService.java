package com.trs.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface MaintenanceService {

	public void doWork(JobExecutionContext context) throws JobExecutionException;

}
