package com.trs.service;

import com.trs.bean.UserEmail;

import java.util.List;

/**
 * Created by zhangheng on 2016/7/27.
 */
public interface UserEmailService {
    /**
     * 获得所有配置的预警邮件接收用户
     * @return
     */
    public List<UserEmail> findAll();

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    public UserEmail findById(Integer id);

    /**
     * 保存或更新一条记录
     * @param userEmail
     * @return
     */
    public UserEmail saveOrUpdate(UserEmail userEmail);

    /**
     * 删除一条记录
     * @param userEmail
     */
    public void delete(UserEmail userEmail);
}
