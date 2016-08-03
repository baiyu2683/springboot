package com.trs.job;

import com.trs.bean.OffsetLimit;
import com.trs.dao.impl.GenericHibernateDAO;
import com.trs.util.PagedArrayList;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class QuartzDaoImpl implements QuartzDao {

	private SessionFactory sessionFactory;

	@Override
	public PagedArrayList<QuartzTriggerInfo> findQuartzTriggerInfos(QuartzTriggerInfoCriterion criterion,
																	OffsetLimit offsetLimit) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(QuartzTriggerInfo.class);
		if (StringUtils.isNotEmpty(criterion.getKeyword())) {
			criteria.add(Restrictions.like("description", criterion.getKeyword(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(criterion.getGroup())) {
			criteria.add(Restrictions.eq("group", criterion.getGroup()));
		}else{
			criteria.add(Restrictions.not(Restrictions.eq("group", "MANUAL_TRIGGER")));// 不显示临时生成的触发器
		}
		if(criterion.getStateType()!=null){
			switch (criterion.getStateType()) {
			case 1:
				criteria.add(Restrictions.eq("lastRunResult", false));
				break;
			case 2:
				criteria.add(Restrictions.or(Restrictions.eq("state", "BLOCKED"), Restrictions.eq("state", "PAUSED_BLOCKED")));
				break;
			case 3:
				criteria.add(Restrictions.eq("state", "WAITING"));
				break;
			}
		}
		if(criterion.getCostType()!=null){
			switch (criterion.getCostType()) {
			case 1:
				criteria.add(Restrictions.lt("lastRunCost", 1000L));
				break;
			case 2:
				criteria.add(Restrictions.between("lastRunCost", 1000L, 9999L));
				break;
			case 3:
				criteria.add(Restrictions.between("lastRunCost", 10000L, 29999L));
				break;
			case 4:
				criteria.add(Restrictions.between("lastRunCost", 30000L, 59999L));
				break;
			case 5:
				criteria.add(Restrictions.ge("lastRunCost", 60000L));
				break;
			}
		}
		if(criterion.getHealthType()!=null){
			switch (criterion.getHealthType()) {
			case 1:
				criteria.add(Restrictions.lt("healthDegree", 20));
				break;
			case 2:
				criteria.add(Restrictions.between("healthDegree", 20, 39));
				break;
			case 3:
				criteria.add(Restrictions.between("healthDegree", 40, 59));
				break;
			case 4:
				criteria.add(Restrictions.between("healthDegree", 60, 79));
				break;
			case 5:
				criteria.add(Restrictions.ge("healthDegree", 80));
				break;
			}
		}
		return GenericHibernateDAO.findByPageEx(criteria, offsetLimit, Order.desc("startTime"));
	}

	@Override
	public QuartzTriggerExtraState getQuartzTriggerExtraState(String name, String group) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(QuartzTriggerExtraState.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("group", group));
		return (QuartzTriggerExtraState) criteria.uniqueResult();
	}

	@Override
	public void saveQuartzTriggerExtraState(QuartzTriggerExtraState state) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(state);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
