package com.trs.test;

import com.trs.main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by zhangheng on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
public class CpmApplicationTests {

    private Logger LOGGER = LoggerFactory.getLogger(CpmApplicationTests.class);

    private String baseUrl;

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception{
        restTemplate = new TestRestTemplate();
        baseUrl = "http://127.0.0.1:" + port +"/useremail";
    }

    @Test
    public void testUserEmailList() throws Exception{
        String url = baseUrl + "/list";
        Map<String, Object> map = new HashMap<>();
        map.put("email","zhangheng2681231231233@gmail.com");
        map.put("name", "张恒");
        ResponseEntity<String> result = restTemplate.getForEntity(url + "?email={email}&name={name}", String.class, map);
        LOGGER.info(result.getBody());
    }

    @Test
    public void testUserEmailAdd() throws Exception{
        String url = baseUrl + "/add";
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < 4; i++){
            sb.append("{");
            Random random = new Random();
            random.nextInt(123123123);
            sb.append("\"name\":" + "\"张恒1\",");
            sb.append("\"email\":" + "\"" + random.nextInt(123123123) + "@qq.com\"");
            sb.append("}");
            if(i < 3)
                sb.append(",");
        }
        sb.append("]");
        Map<String, Object> map = new HashMap<>();
        map.put("userEmailJsonData",sb.toString());
        RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.POST, null);
        ResponseEntity<String> result = restTemplate.postForEntity(url + "?userEmailJsonData={userEmailJsonData}", requestEntity, String.class, map);
        LOGGER.info(result.getBody());
    }

    @Test
    public void testUserEmaildelete() throws Exception{
        String url = baseUrl + "/delete";
        Map<String, Object> map = new HashMap<>();
        map.put("userEmailJsonData","2");
        ResponseEntity<String> result = restTemplate.exchange(url + "?userEmailJsonData={userEmailJsonData}", HttpMethod.DELETE,null, String.class, map);
        LOGGER.info(result.getBody());
    }

    @Test
    public void testUserEmailUpdate() throws Exception{
        String url = baseUrl + "/update";
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append("{");
        sb.append("\"id\":" + "\"9\",");
        sb.append("\"name\":" + "\"张恒edfvgtreds\",");
        sb.append("\"email\":" + "\"edfvgtreds@qq.com\"");
        sb.append("},");

        sb.append("{");
        sb.append("\"id\":" + "\"10\",");
        sb.append("\"name\":" + "\"张恒1qaz\",");
        sb.append("\"email\":" + "\"1qaz@qq.com\"");
        sb.append("},");

        sb.append("{");
        sb.append("\"id\":" + "\"1\",");
        sb.append("\"name\":" + "\"张恒123456\",");
        sb.append("\"email\":" + "\"123456@qq.com\"");
        sb.append("}");
        sb.append("]");
        Map<String, Object> map = new HashMap<>();
        map.put("userEmailJsonData", sb.toString());
        ResponseEntity<String> result = restTemplate.exchange(url + "?userEmailJsonData={userEmailJsonData}", HttpMethod.PUT,null, String.class, map);
        LOGGER.info(result.getBody());
    }
}
