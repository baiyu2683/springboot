package com.trs.service;

import com.trs.bean.UserEmail;

import java.util.List;
import java.util.Map;

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
     * 返回数据和信息
     * @param pageNumber 页码
     * @param pageSize   每页个数
     * @param name   站点名
     * @param email    频道
     * @return
     */
    public Map<String,Object> findUserEmail(Integer pageNumber, Integer pageSize, String name, String email);

    /**
     * 返回没有重复值的列表
     * @return
     */
    List<UserEmail> findUserEmailNoDuplicate();

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    public UserEmail findById(Integer id);

    /**
     * 保存或更新一条记录
     * @param userEmailJsonData
     * @return
     */
    public List<Map<String, Object>> addUserEmail(String userEmailJsonData);

    /**
     * 根据id列表删除数据
     * @param ids
     * @return
     */
    public List<Map<String, Object>> deleteByIds(String ids);

    /**
     * 更新邮件列表信息
     * @param userEmailJsonData
     * @return
     */
    public List<Map<String, Object>> updateUserEmails(String userEmailJsonData);
}
