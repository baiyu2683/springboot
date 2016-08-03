package com.trs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.trs")
@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource("quartzContext.xml")
public class Application {

	public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    } 
}
