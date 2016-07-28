package com.trs.ui;

import com.trs.bean.UserEmail;
import com.trs.dao.UserEmailDao;
import com.trs.service.UserEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

}
