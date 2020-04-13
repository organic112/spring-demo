package com.potato112.springdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients //FIXME auth stopped working
public class DemoApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApp.class);

    public static void main(String[] args) {

        try {
            ApplicationContext context = SpringApplication.run(DemoApp.class, args);

        } catch (Exception e) {
            String errorMessage = "Application encountered unrecoverable error.";
            LOGGER.debug(errorMessage + e.getLocalizedMessage());
        }
    }
}
