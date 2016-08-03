package com.trs.job;

import org.quartz.*;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TestMaintenanceService implements MaintenanceService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(TestMaintenanceService.class);

	public void doWork(JobExecutionContext context) {
		try {
			Scheduler scheduler=context.getScheduler();
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug(new Date()+":"+context.getJobDetail());
			}
			String[] triggerGroupNames=scheduler.getTriggerGroupNames();
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Triggers:");
			}
			if(triggerGroupNames!=null){
				for(String group:triggerGroupNames){
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("  "+group+":");
					}
					String[] triggerNames=scheduler.getTriggerNames(group);
					if(triggerNames!=null){
						for(String name:triggerNames){
							Trigger trigger=scheduler.getTrigger(name, group);
							if(LOGGER.isDebugEnabled()){
								LOGGER.debug("    "+trigger);
							}
						}
					}
				}
			}

			String[] jobGroupNames=scheduler.getJobGroupNames();
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Jobs:");
			}
			if(jobGroupNames!=null){
				for(String group:jobGroupNames){
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("  "+group+":");
					}
					String[] jobNames=scheduler.getJobNames(group);
					if(jobNames!=null){
						for(String name:jobNames){
							JobDetail jobDetail=scheduler.getJobDetail(name, group);
							if(LOGGER.isDebugEnabled()){
								LOGGER.debug("    "+jobDetail);
							}
						}
					}
				}
			}
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

}
