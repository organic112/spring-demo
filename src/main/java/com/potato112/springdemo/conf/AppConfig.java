package com.potato112.springdemo.conf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableAsync
@ComponentScan({"com.potato112.springdemo"})
public class AppConfig implements CommandLineRunner {

    private GlobalProperties globalProperties;

    public AppConfig(GlobalProperties globalProperties) {
        this.globalProperties = globalProperties;
    }

    /**
     * Application execution main flow
     */
    @PostConstruct
    public void init() {

    }

    /**
     * application initialization for different modes
     */
    @Override
    public void run(String... args) throws Exception {

        List<String> arguments = new ArrayList<>(Arrays.asList(args));

        String arg1 = null;
        String arg2 = null;

        if (arguments.size() > 0) {
            arg1 = arguments.get(0);
            arg2 = arguments.get(1);
        }

        String liveConfigurationProperties;
        String authProps;
        String appWorkingDirectory;

        if (arguments.size() == 2) {

            System.out.println("arg1:" + arg1);

            if (arg1.equals("docker") && arg2.equals("live")) {
                System.out.println("RUNNING  PRODUCTION MODE...");
            }

        } else {

            // no args for unit tests just start Spring context
            System.out.println("no args passed to application");
            System.out.println("RUNNING SPRING INTEGRATION TESTS MODE...");
        }
    }
}