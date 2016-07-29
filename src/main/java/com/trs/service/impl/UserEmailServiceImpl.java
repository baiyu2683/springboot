package com.trs.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.bean.OffsetLimit;
import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailCriterion;
import com.trs.dao.UserEmailDao;
import com.trs.service.UserEmailService;
import com.trs.system.Const;
import com.trs.util.PagedArrayList;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

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
    public List<Map<String, Object>> addUserEmail(String userEmailJsonData) {
        List<Map<String, Object>> list = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        if(StringUtils.isNotBlank(userEmailJsonData)){
            try {
                JavaType javaType = om.getTypeFactory().constructCollectionType(Set.class,UserEmail.class);
                Set<UserEmail> userEmails = om.readValue(userEmailJsonData, javaType);
                for(UserEmail ue : userEmails){
                    UserEmailCriterion criterion = new UserEmailCriterion();
                    criterion.setName(ue.getName());
                    criterion.setEmail(ue.getEmail());
                    UserEmail userEmail = userEmailDao.findByNameAndEmail(criterion);
                    if (userEmail == null){
                        userEmail = userEmailDao.makePersistent(ue);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", "success");
                        map.put("data", userEmail);
                        list.add(map);
                    }else{
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", "failure");
                        map.put("errorMsg", Const.RETURN_MESSAGE_DUPLICATE);
                        map.put("data", userEmail);
                        list.add(map);
                    }
                }
            } catch (IOException e) {
                LOGGER.info("转换失败!", e);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> deleteByIds(String ids) {
        List<Map<String, Object>> list = new ArrayList<>();
        if(StringUtils.isNotBlank(ids)){
            try{
                String[] idsStr = ids.split(",");
                for(String id : idsStr){
                    try {
                        UserEmail ue = userEmailDao.findById(Integer.parseInt(id), false);
                        userEmailDao.makeTransient(ue);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", Const.RETURN_RESULT_SUCCES);
                        map.put("data", id);
                        list.add(map);
                    }catch (Exception e){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", Const.RETURN_RESULT_FAILURE);
                        map.put("errorMsg", Const.RETURN_MESSAGE_PARAMETERERROR);
                        map.put("data", id);
                        list.add(map);
                    }
                }
            }catch(Exception e){
                LOGGER.error("转换失败!", e);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> updateUserEmails(String userEmailJsonData) {
        List<Map<String, Object>> list = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        if(StringUtils.isNotBlank(userEmailJsonData)){
            try {
                JavaType javaType = om.getTypeFactory().constructCollectionType(List.class,UserEmail.class);
                List<UserEmail> userEmails = om.readValue(userEmailJsonData, javaType);
                for(UserEmail ue : userEmails){
                    Integer id = ue.getId();
                    UserEmail userEmail = userEmailDao.findById(id, true);
                    try{
                        userEmail.setName(ue.getName());
                        userEmail.setEmail(ue.getEmail());
                        userEmail = userEmailDao.makePersistent(userEmail);
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", Const.RETURN_RESULT_SUCCES);
                        map.put("data", userEmail);
                        list.add(map);
                    }catch(Exception e){
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("result", Const.RETURN_RESULT_FAILURE);
                        map.put("errorMsg", Const.RETURN_MESSAGE_PARAMETERERROR);
                        map.put("data", ue);
                        list.add(map);
                    }
                }
            } catch (IOException e) {
                LOGGER.info("转换失败!", e);
            }
        }
        return list;
    }
}
