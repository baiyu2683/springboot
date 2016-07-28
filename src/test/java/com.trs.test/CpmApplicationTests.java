package com.trs.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangheng on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CpmApplicationTests.class)
@WebIntegrationTest
public class CpmApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testRequest() throws Exception{
    }
}
