package com.trs.service.impl;

import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailDao;
import com.trs.service.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service
@Transactional
public class UserEmailServiceImpl implements UserEmailService {

    @Autowired
    private UserEmailDao userEmailDao;

    @Override
    public List<UserEmail> findAll() {
        return userEmailDao.findAll();
    }

    @Override
    public UserEmail findById(Integer id) {
        return userEmailDao.findById(id,false);
    }

    @Override
    public UserEmail saveOrUpdate(UserEmail userEmail) {
        return userEmailDao.makePersistent(userEmail);
    }

    @Override
    public void delete(UserEmail userEmail) {
        userEmailDao.makeTransient(userEmail);
    }
}
