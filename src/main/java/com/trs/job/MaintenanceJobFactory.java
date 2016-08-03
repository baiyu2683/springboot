package com.trs.job;

import com.trs.service.JobService;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

public class MaintenanceJobFactory implements StatefulJob {

	public void execute(JobExecutionContext context) throws JobExecutionException {

			SchedulerContext schedulerContext;
			try {
				schedulerContext = context.getScheduler().getContext();
			}
			catch (SchedulerException e) {
				throw new JobExecutionException(e);
			}

			ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get("applicationContext");
			String serviceName=context.getMergedJobDataMap().getString("serviceName");
			MaintenanceService service = (MaintenanceService)applicationContext.getBean(serviceName);

			if(context.getTrigger().isVolatile()){
				// 临时生成的Trigger不进行运行监控统计，直接运行任务后返回
				service.doWork(context);
				return;
			}
			// 非临时生成的Trigger需要进行运行监控统计
			String triggerName=context.getTrigger().getName();
			String triggerGroup=context.getTrigger().getGroup();
			JobService jobService=applicationContext.getBean("jobService",JobService.class);
			QuartzTriggerExtraState state=jobService.getQuartzTriggerExtraState(triggerName, triggerGroup);
			if(state==null){
				state=new QuartzTriggerExtraState();
				state.setName(triggerName);
				state.setGroup(triggerGroup);
			}
			long begin=System.currentTimeMillis();
			try {
				service.doWork(context);
				state.setLastRunResult(true);
				state.setLastSuccessTime(new Date());
				state.setTotalSuccessCount(state.getTotalSuccessCount()+1);
				state.setLastTenResult(state.getLastTenResult()+"1");
			}
			catch (Exception e) {
				e.printStackTrace();
				state.setLastRunResult(false);
				state.setLastFailTime(new Date());
				state.setTotalFailCount(state.getTotalFailCount()+1);
				state.setLastTenResult(state.getLastTenResult()+"0");
				if(e instanceof JobExecutionException)
					throw (JobExecutionException)e;
				else
					throw new JobExecutionException(e);
			}finally{
				state.setLastRunCost(System.currentTimeMillis()-begin);
				state.setTotalRunCount(state.getTotalRunCount()+1);
				if(state.getLastTenResult().length()>QuartzTriggerExtraState.LAST_NCOUNT)
					state.setLastTenResult(StringUtils.right(state.getLastTenResult(), QuartzTriggerExtraState.LAST_NCOUNT));
				// 计算最近N次运行的成功率
				if (StringUtils.isNotEmpty(state.getLastTenResult())) {
					state.setLastTenTotalCount(state.getLastTenResult().length());
					state.setLastTenSuccessCount(StringUtils.countMatches(state.getLastTenResult(), "1"));
					state.setLastTenFailCount(state.getLastTenTotalCount()-state.getLastTenSuccessCount());
					state.setHealthDegree(state.getLastTenSuccessCount()*100/state.getLastTenTotalCount());
				}
				jobService.saveQuartzTriggerExtraState(state);
			}

	}

}
