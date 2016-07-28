package com.trs.dao;


import com.trs.bean.OffsetLimit;
import com.trs.bean.UserEmail;
import com.trs.util.PagedArrayList;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by zhangheng on 16-7-26.
 */
public interface UserEmailDao extends GenericDAO<UserEmail, Integer>{
    /**
     * 根据用户名和邮箱查询一条记录
     * @param criterion
     * @return
     */
    UserEmail findByNameAndEmail(UserEmailCriterion criterion);

    /**
     * 根据条件分页查询
     * @param criterion
     * @param offsetLimit
     * @return
     */
    PagedArrayList<UserEmail> findPaged(UserEmailCriterion criterion, OffsetLimit offsetLimit);
}
