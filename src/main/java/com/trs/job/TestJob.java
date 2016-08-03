package com.trs.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by zhangheng on 16-8-4.
 */
public class TestJob implements MaintenanceService {
    @Override
    public void doWork(JobExecutionContext context) throws JobExecutionException {
        System.out.println("asdfasdfasdf");
    }
}
