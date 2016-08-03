package com.trs.service;

import com.trs.bean.OffsetLimit;
import com.trs.job.QuartzTriggerExtraState;
import com.trs.job.QuartzTriggerInfo;
import com.trs.job.QuartzTriggerInfoCriterion;
import com.trs.util.PagedArrayList;
import org.quartz.Trigger;

/**
 * Quartz job相关服务
 * @author chang
 * @since 2012-9-11 13:35:55
 * */
public interface JobService {

    // TODO 将Quartz Scheduler的接口封装到这个接口里，去掉系统其他接口对Quartz的直接依赖

    /**
     * 获取Quartz中触发器的所有分组.
     *
     * @return the trigger group names
     */
    String[] getTriggerGroupNames();

    /**
     * 获取指定的Quartz触发器
     * */
    Trigger getTrigger(String name, String group);

    /**
     * 立即运行任务
     * */
    void startTrigger(String triggerName, String groupName);

    /**
     * 暂停触发器
     * */
    void pauseTrigger(String triggerName, String groupName);

    /**
     * 恢复触发器
     * */
    void resumeTrigger(String triggerName, String groupName);

    /**
     * 设置任务执行的时间间隔.
     *
     * @param name 触发器名称
     * @param group 触发器分组
     * @param repeatInterval 新的运行间隔
     * @return the string
     */
    String setRepeatInterval(String name, String group, Long repeatInterval);

    /**
     * 设置任务执行的cron表达式.
     *
     * @param name 触发器名称
     * @param group 触发器分组
     * @param cronExpression 新的cron表达式
     * @return the string
     */
    String setCronExpression(String name, String group, String cronExpression);

    /**
     * 获取指定的{@link QuartzTriggerExtraState}对象.
     *
     * @param name 触发器名称
     * @param group 触发器分组
     * @return {@link QuartzTriggerExtraState}对象
     */
    QuartzTriggerExtraState getQuartzTriggerExtraState(String name, String group);

    /**
     * 保存{@link QuartzTriggerExtraState}对象.
     *
     * @param state {@link QuartzTriggerExtraState}对象
     */
    void saveQuartzTriggerExtraState(QuartzTriggerExtraState state);

    /**
     * 根据关键词和分组名查询{@link QuartzTriggerInfo}对象列表.
     *
     * @param criterion 查询条件
     * @param offsetLimit 分页条件
     * @return 满足条件的{@link QuartzTriggerInfo}对象列表
     */
    PagedArrayList<QuartzTriggerInfo> listQuartzTriggerInfos(QuartzTriggerInfoCriterion criterion, OffsetLimit offsetLimit);

}