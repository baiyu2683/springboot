package com.trs.dao;


import com.trs.bean.UserEmail;

import java.util.List;

/**
 * Created by zhangheng on 16-7-26.
 */
public interface UserEmailDao extends GenericDAO<UserEmail, Integer>{
    /**
     * 根据条件查询
     * @param criterion
     * @return
     */
    List<UserEmail> findByCriterion(UserEmailCriterion criterion);

    /**
     * 根据用户名和邮箱查询一条记录
     * @param criterion
     * @return
     */
    UserEmail findByNameAndEmail(UserEmailCriterion criterion);
}
