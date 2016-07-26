package com.trs.dao;

import com.trs.bean.UserEmail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by zhangheng on 16-7-26.
 */
@Repository
@Transactional
public class UserEmailDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(UserEmail userEmail){
        getSession().delete(userEmail);
    }

}
