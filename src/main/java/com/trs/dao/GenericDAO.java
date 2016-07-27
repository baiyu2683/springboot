package com.trs.dao;

import com.trs.bean.OffsetLimit;
import com.trs.util.PagedArrayList;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 泛型DAO接口。
 * @author wengjing
 *
 * @param <T> 实体类型。
 * @param <ID> 实体ID的类型。
 */
public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id, boolean lock);

	List<T> findAll();

	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	List<T> findAll(T exampleInstance, Object[] between, String order, String... excludeProperty);
	
	PagedArrayList<T> findPage(T exampleInstance, Object[] between, String order, OffsetLimit offsetLimit, String... excludeProperty);

	T makePersistent(T entity);

	void makeTransient(T entity);

	void batchSave(Collection<T> entities);
}
