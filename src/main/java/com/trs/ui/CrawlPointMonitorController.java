package com.trs.ui;

import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailDao;
import com.trs.service.UserEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangheng on 2016/7/27.
 */
@RestController
@RequestMapping(value ="/crawlpointmonitor")
public class CrawlPointMonitorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrawlPointMonitorController.class);

    @Autowired
    private UserEmailService userEmailService;

    public void setUserEmailService(UserEmailService userEmailService) {
        this.userEmailService = userEmailService;
    }

    @RequestMapping(value="/list")
    public List<UserEmail> list(){
        List<UserEmail> list = userEmailService.findAll();
        if(list != null){
            for(UserEmail ue : list){
                LOGGER.info("id:" + ue.getId() + " name:" + ue.getName() + " email:" + ue.getEmail());
            }
        }
        return list;
    }

    @RequestMapping(value="add")
    public Map add(){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setName("zhheng123123");
        ue.setEmail("zhangheng2681231231233@gmail.com");
        userEmailService.saveOrUpdate(ue);
        map.put("result","success");
        return map;
    }

    @RequestMapping(value="delete")
    public Map delete(){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setId(1);
        userEmailService.delete(ue);
        map.put("result","success");
        return map;
    }

    @RequestMapping(value="update")
    public Map update(){
        Map<String, Object> map = new HashMap<String, Object>();
        UserEmail ue = new UserEmail();
        ue.setId(1);
        ue.setName("zhheng");
        ue.setEmail("zhangheng2683@gmail.com");
        userEmailService.saveOrUpdate(ue);
        map.put("result","success");
        return map;
    }
}
