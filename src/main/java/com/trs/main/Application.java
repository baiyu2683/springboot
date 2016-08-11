package com.trs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.trs")
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    } 
}
