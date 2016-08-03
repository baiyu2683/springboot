package com.trs.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.trs.bean.OffsetLimit;
import com.trs.exception.TRSOMException;
import com.trs.job.QuartzDao;
import com.trs.job.QuartzTriggerExtraState;
import com.trs.job.QuartzTriggerInfo;
import com.trs.job.QuartzTriggerInfoCriterion;
import com.trs.service.EncryptService;
import com.trs.service.JobService;
import com.trs.util.PagedArrayList;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jobService")
public class JobServiceImpl implements JobService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private QuartzDao quartzDao;

    public JobServiceImpl() {
//        EncryptService obj;
//        try {
//            obj = EncryptService.getInstance();
//            obj.checkLicence();
//        }
//        catch (Exception e) {
//            throw new TRSOMException("无法获得或者校验注册码：" + e.getMessage(), e);
//        }
    }

    @Override
    @Transactional
    public Trigger getTrigger(String name, String group) {
        try{
            TriggerKey triggerKey = new TriggerKey(name, group);
            return scheduler.getTrigger(triggerKey);
        }
        catch (SchedulerException e) {
            throw new TRSOMException("获取触发器失败:"+e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public String setRepeatInterval(String name, String group, Long repeatInterval) {
//        SimpleTrigger trigger = (SimpleTrigger) this.getTrigger(name, group);
//        trigger.setRepeatInterval(repeatInterval);
//        try {
//            Date date = scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
//            if (date == null)
//                return "设置失败";
//        }
//        catch (SchedulerException e) {
//            throw new TRSOMException("修改触发器失败："+e.getMessage(), e);
//        }
        return "设置成功";
    }

    @Override
    @Transactional
    public String setCronExpression(String name, String group, String cronExpression) {
        CronTrigger trigger = (CronTrigger) this.getTrigger(name, group);
//        try {
//            trigger.setCronExpression(cronExpression);
//        }
//        catch (ParseException e1) {
//            return "设置失败：定时表达式语法错误";
//        }
//        try {
//            Date date = scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(), trigger);
//            if (date == null)
//                return "设置失败";
//        }
//        catch (SchedulerException e) {
//            throw new TRSOMException("修改触发器失败："+e.getMessage(), e);
//        }
        return "设置成功";
    }



    @Override
    @Transactional
    public void startTrigger(String triggerName, String groupName) {
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
            Trigger trigger=scheduler.getTrigger(triggerKey);
//            scheduler.triggerJobWithVolatileTrigger(trigger.getJobName(), trigger.getJobGroup());
            scheduler.triggerJob(trigger.getJobKey());
        }
        catch (SchedulerException e) {
            throw new TRSOMException("启动任务失败："+e.getMessage(), e);
        }

    }

    @Override
    @Transactional
    public void pauseTrigger(String triggerName, String groupName) {
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
            scheduler.pauseTrigger(triggerKey);
        }
        catch (SchedulerException e) {
            throw new TRSOMException("暂停任务失败："+e.getMessage(), e);
        }

    }

    @Override
    @Transactional
    public void resumeTrigger(String triggerName, String groupName) {
        try {
            TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
            scheduler.resumeTrigger(triggerKey);
        }
        catch (SchedulerException e) {
            throw new TRSOMException("恢复任务失败："+e.getMessage(), e);
        }

    }

    @Override
    @Transactional
    public QuartzTriggerExtraState getQuartzTriggerExtraState(String name, String group) {
        return quartzDao.getQuartzTriggerExtraState(name, group);
    }

    @Override
    @Transactional
    public void saveQuartzTriggerExtraState(QuartzTriggerExtraState state) {
        quartzDao.saveQuartzTriggerExtraState(state);

    }

    @Override
    @Transactional
    public PagedArrayList<QuartzTriggerInfo> listQuartzTriggerInfos(QuartzTriggerInfoCriterion criterion,
                                                                    OffsetLimit offsetLimit) {
        PagedArrayList<QuartzTriggerInfo> result = quartzDao.findQuartzTriggerInfos(criterion, offsetLimit);
        for (QuartzTriggerInfo info : result.getPageData()) {
            if (info.getNextFireTime() != null)
                info.setNextFireTime2(new Date(info.getNextFireTime()));
            if (info.getPrevFireTime() != null)
                info.setPrevFireTime2(new Date(info.getPrevFireTime()));
            if (info.getStartTime() != null)
                info.setStartTime2(new Date(info.getStartTime()));
            if (info.getEndTime() != null)
                info.setEndTime2(new Date(info.getEndTime()));
        }
        return result;
    }

    @Override
    @Transactional
    public String[] getTriggerGroupNames() {
        try {
            List<String> list = scheduler.getTriggerGroupNames();
            if(list == null) return new String[0];
            return list.toArray(new String[list.size()]);
        }
        catch (SchedulerException e) {
            throw new TRSOMException("获取触发器分组名列表失败:" + e.getMessage(), e);
        }
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setQuartzDao(QuartzDao quartzDao) {
        this.quartzDao = quartzDao;
    }

}