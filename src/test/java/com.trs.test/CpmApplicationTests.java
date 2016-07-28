package com.trs.test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.main.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

/**
 * Created by zhangheng on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=9005")
public class CpmApplicationTests {

    private Logger LOGGER = LoggerFactory.getLogger(CpmApplicationTests.class);

    private String url;

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception{
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void testUserEmailList() throws Exception{
        url = "http://localhost:9005/useremail/list";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        LOGGER.info(response.getBody());
    }

    @Test
    public void testUserEmailAdd() throws Exception{
        url = "http://localhost:9005/useremail/add";
        RequestEntity<String> request = new RequestEntity<String>(HttpMethod.POST, null);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        LOGGER.info(response.getBody());
    }

    @Test
    public void testUserEmailUpdate() throws Exception{
        url = "http://localhost:9005/useremail/update";
    }
}
