package com.trs.dao.impl;

import com.trs.bean.OffsetLimit;
import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailCriterion;
import com.trs.dao.UserEmailDao;
import com.trs.util.PagedArrayList;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
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
    public UserEmail findByNameAndEmail(UserEmailCriterion criterion){
        return (UserEmail) buildCriteria(criterion).uniqueResult();
    }

    @Override
    public PagedArrayList<UserEmail> findPaged(UserEmailCriterion criterion, OffsetLimit offsetLimit) {
        Criteria criteria = this.buildCriteria(criterion);
        Projection projection = Projections.projectionList()
                .add(Projections.property("id"),"id")
                .add(Projections.property("name"),"name")
                .add(Projections.groupProperty("email"),"email");
        return super.findByPage(criteria, projection, offsetLimit);
    }

    @Override
    public PagedArrayList<UserEmail> findNoDuplicated() {
        //测试groupby排重
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserEmail.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id"),"id")
                .add(Projections.property("name"),"name")
                .add(Projections.groupProperty("email"),"email"));
        criteria.setResultTransformer(Transformers.aliasToBean(UserEmail.class));
        List<UserEmail> list = criteria.list();
        return null;
    }
}
