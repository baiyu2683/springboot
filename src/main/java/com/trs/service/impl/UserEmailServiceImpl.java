package com.trs.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.bean.OffsetLimit;
import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailCriterion;
import com.trs.dao.UserEmailDao;
import com.trs.service.UserEmailService;
import com.trs.system.Const;
import com.trs.util.PagedArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service
@Transactional
public class UserEmailServiceImpl implements UserEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEmailServiceImpl.class);

    @Autowired
    private UserEmailDao userEmailDao;

    @Override
    public List<UserEmail> findAll() {
        return userEmailDao.findAll();
    }

    @Override
    public Map<String, Object> findUserEmail(Integer pageNumber, Integer pageSize, String name, String email) {
        Map<String, Object> map = new LinkedHashMap<>();
        ObjectMapper om = new ObjectMapper();
        try{
            UserEmailCriterion criterion = new UserEmailCriterion();
            criterion.setName(name);
            criterion.setEmail(email);
            if(pageNumber == null) pageNumber = Const.NUM_ONE;
            if(pageSize == null) pageSize = Const.PAGE_SIZE;
            OffsetLimit offsetLimit = new OffsetLimit((pageNumber - Const.NUM_ONE) * pageSize, pageSize);
            PagedArrayList<UserEmail> userEmails = userEmailDao.findPaged(criterion, offsetLimit);
            map.put("result", Const.RETURN_RESULT_SUCCES);
            map.put("pageNumber", pageNumber);
            map.put("pageSize", pageSize);
            map.put("totalRecords", userEmails.getTotalRecords());
            map.put("totalPages", userEmails.getTotalPages());
            map.put("name", name);
            map.put("email", email);
            map.put("data",userEmails.getPageData());
        }catch(Exception e){
            LOGGER.error("查询预警邮件接收用户列表出错", e);
            map.put("result", Const.RETURN_RESULT_FAILURE);
            map.put("pageNumber", pageNumber);
            map.put("pageSize", pageSize);
            map.put("name", name);
            map.put("email", email);
            map.put("data","[]");
            map.put("errorMsg", Const.RETURN_MESSAGE_SYSTEMERROR);
        }
        return map;
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
