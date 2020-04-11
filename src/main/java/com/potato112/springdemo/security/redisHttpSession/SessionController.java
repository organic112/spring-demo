package com.potato112.springdemo.security.redisHttpSession;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller that provides String content.
 * When request is made and authenticated user starts new Http session.
 */
@RestController
public class SessionController {
    @RequestMapping("/")
    public String helloAdmin() {
        return "hello admin";
    }
}
