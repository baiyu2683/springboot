package com.trs.ui;

import com.trs.bean.UserEmail;
import com.trs.message.BuildReturnMessage;
import com.trs.service.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        return BuildReturnMessage.buildMessage(map);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public Map<String,Object> add(){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setName("zhheng123123");
        ue.setEmail("zhangheng2681231231233@gmail.com");
        userEmailService.saveOrUpdate(ue);
        map.put("result","success");
        return map;
    }

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public Map<String,Object> delete(){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setId(1);
        userEmailService.delete(ue);
        map.put("result","success");
        return map;
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public Map<String,Object> update(@RequestParam(value = "id")String id){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setId(1);
        ue.setName("追忆流年时光");
        ue.setEmail("zhuiyiliunianshiguang@gmail.com");
        userEmailService.saveOrUpdate(ue);
        map.put("result","success");
        map.put("json",id);
        return map;
    }
}
