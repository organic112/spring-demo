package com.potato112.springdemo.redisHttpSession;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller that provides String content when request is made
 * and authenticated user starts new Http session.
 */
@RestController
public class SessionController {
    @RequestMapping("/")
    public String helloAdmin() {
        return "hello admin";
    }
}
