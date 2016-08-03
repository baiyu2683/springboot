package com.trs.job;

import com.trs.bean.OffsetLimit;
import com.trs.util.PagedArrayList;

/**
 * 维护Quartz附加信息的DAO接口，主要是任务状态信息的维护.
 */
public interface QuartzDao {

	/**
	 * 根据关键词和分组名查询{@link QuartzTriggerInfo}对象列表.
	 *
	 * @param criterion 查询条件
	 * @param offsetLimit 分页条件
	 * @return 满足条件的{@link QuartzTriggerInfo}对象列表
	 */
	PagedArrayList<QuartzTriggerInfo> findQuartzTriggerInfos(QuartzTriggerInfoCriterion criterion, OffsetLimit offsetLimit);

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

}
