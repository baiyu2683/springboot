package com.trs.ui;

import com.trs.bean.UserEmail;
import com.trs.service.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangheng on 2016/7/28.
 */
@RestController
@RequestMapping(value = "/useremail")
public class UserEmailController {
    @Autowired
    private UserEmailService userEmailService;

    public void setUserEmailService(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public Map<String,Object> list(@RequestParam(value = "pageNumber",required = false)Integer pageNumber,
                                   @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                   @RequestParam(value = "name",required = false)String name,
                                   @RequestParam(value = "email",required = false)String email){
        Map<String,Object> map = userEmailService.findUserEmail(pageNumber, pageSize, name, email);
        return map;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public List<Map<String, Object>> add(@RequestParam(value = "userEmailJsonData", required = true)String userEmailJsonData){
        List<Map<String, Object>> list = userEmailService.addUserEmail(userEmailJsonData);
        return list;
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public List<Map<String, Object>> delete(@RequestParam(value = "userEmailJsonData", required = true)String ids){
        List<Map<String, Object>> list = userEmailService.deleteByIds(ids);
        return list;
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public List<Map<String, Object>> update(@RequestParam(value = "userEmailJsonData")String userEmailJsonData){
        List<Map<String, Object>> list = userEmailService.updateUserEmails(userEmailJsonData);
        return list;
    }
}
