package com.trs.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

/**
 * Created by zhangheng on 16-8-4.
 */
@Component
public class TestSpringBootScheduleTask {

    @Scheduled(fixedRate = 60*1000)
    public void showPerMinute(){
        System.out.println("showPerMinute");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void testCronExpression(){
        System.out.println("testCronExpression");
    }

}
