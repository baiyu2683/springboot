package com.trs.dao.impl;

import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailCriterion;
import com.trs.dao.UserEmailDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangheng on 2016/7/27.
 */
@Repository
public class UserEmailDaoImpl extends GenericHibernateDAO<UserEmail, Integer>  implements UserEmailDao {

    private Criteria buildCriteria(UserEmailCriterion criterion){
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserEmail.class);
        if(criterion == null) return criteria;
        if(null != criterion.getId()){
            criteria.add(Restrictions.eq("id", criterion.getId()));
        }
        if(StringUtils.isNotBlank(criterion.getName())){
            criteria.add(Restrictions.eq("name", criterion.getName()));
        }
        if(StringUtils.isNotBlank(criterion.getEmail())){
            criteria.add(Restrictions.eq("email", criterion.getEmail()));
        }
        if(null != criterion.getIds() && (criterion.getIds().length >= 1)){
            criteria.add(Restrictions.in("id", criterion.getIds()));
        }
        return criteria;
    }

    @Override
    public List<UserEmail> findByCriterion(UserEmailCriterion criterion) {
        return buildCriteria(criterion).list();
    }

    @Override
    public UserEmail findByNameAndEmail(UserEmailCriterion criterion){
        return (UserEmail) buildCriteria(criterion).uniqueResult();
    }
}
