package com.potato112.springdemo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Provides custom Thread configuration for concurrent services
 */
@Configuration
public class ThreadConfig {

    @Bean
    public ExecutorService myExecutorService() {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }
}



