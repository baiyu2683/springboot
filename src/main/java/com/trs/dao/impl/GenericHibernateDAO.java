package com.trs.dao.impl;

import com.trs.bean.OffsetLimit;
import com.trs.dao.GenericDAO;
import com.trs.util.PagedArrayList;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 使用Hibernate ORM实现的泛型DAO接口。
 * @author wengjing
 *
 * @param <T> 实体类型。
 * @param <ID> 实体ID的类型。
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	// fields ---------------------------------------------------------------------
	private Class<T> persistentClass;
	@Autowired
	private SessionFactory sessionFactory;

	protected int batchSize=20;

	// methods ---------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria crit = session.createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();

	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(T exampleInstance,Object[] between, String order, String... excludeProperty) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(getPersistentClass());
		if(exampleInstance!=null){
			Example example = Example.create(exampleInstance);
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
			criteria.add(example);
		}
		if(between!=null){
			if(between.length>2)
				criteria.add(Restrictions.lt(between[0].toString(),between[2]));
			criteria.add(Restrictions.ge(between[0].toString(),between[1]));
		}
		if(order!=null)
			criteria.addOrder(Order.desc(order));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public PagedArrayList<T> findPage(T exampleInstance,Object[] between, String order, OffsetLimit offsetLimit, String... excludeProperty) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(getPersistentClass());
		
		if(exampleInstance!=null){
			Example example = Example.create(exampleInstance);
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
			criteria.add(example);
		}
		
		int count = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		if (0 == count)
			return new PagedArrayList<T>();
		criteria.setProjection(null).setFirstResult((int) offsetLimit.getOffset())
				.setMaxResults(offsetLimit.getLimit());


		if(between!=null){
			if(between.length>2)
				criteria.add(Restrictions.lt(between[0].toString(),between[2]));
			criteria.add(Restrictions.ge(between[0].toString(),between[1]));
		}
		if(order!=null)
			criteria.addOrder(Order.desc(order));
		return new PagedArrayList<T>(criteria.list(), count, offsetLimit.getOffset(), offsetLimit.getLimit());
	}


	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		Session session = getSessionFactory().getCurrentSession();
		T entity;
		if (lock)
			entity = (T) session.get(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) session.get(getPersistentClass(), id);

		return entity;

	}

	public T makePersistent(T entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entity);
		// session.flush();
		return entity;
	}

	public void makeTransient(T entity) {
		Session session = getSessionFactory().getCurrentSession();
		session.delete(entity);
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria crit = session.createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public static <T> List<T> findAllEx(Criteria criteria, Order... orders) {
		int count = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		if (0 == count)
			return new ArrayList<T>();
		criteria.setProjection(null);
		if (null != orders) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		return criteria.list();
	}

	protected PagedArrayList<T> findByPage(Criteria criteria, OffsetLimit offsetLimit, Order... orders) {
		int count = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		if (0 == count)
			return new PagedArrayList<T>();
		criteria.setProjection(null).setFirstResult((int) offsetLimit.getOffset())
				.setMaxResults(offsetLimit.getLimit());
		if (null != orders) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		List list = criteria.list();
		return new PagedArrayList<T>(list, count, offsetLimit.getOffset(), offsetLimit.getLimit());
	}

	public static <T> PagedArrayList<T> findByPageEx(Criteria criteria, OffsetLimit offsetLimit, Order... orders) {
		int count = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		if (0 == count)
			return new PagedArrayList<T>();
		criteria.setProjection(null).setFirstResult((int) offsetLimit.getOffset())
				.setMaxResults(offsetLimit.getLimit());
		if (null != orders) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
		List list = criteria.list();
		return new PagedArrayList<T>(list, count, offsetLimit.getOffset(), offsetLimit.getLimit());
	}

	/**
	 * 批量保存实体对象.
	 *
	 * @param entities 实体对象集合
	 */
	public void batchSave(Collection<T> entities){
		if(entities==null||entities.isEmpty())
			return;
		Session session=getSessionFactory().getCurrentSession();
		int count=0;
		for(T entity:entities){
			count++;
			session.save(entity);
			if(count%batchSize==0){
				session.flush();
				session.clear();
			}
		}
	}

	// accessors ---------------------------------------------------------------------
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * 获取批量操作的大小.
	 *
	 * @return 批量操作的大小，默认20
	 */
	public int getBatchSize() {
		return batchSize;
	}

	/**
	 * 设置批量操作的大小.
	 *
	 * @param batchSize 批量操作的大小，默认20
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
}
