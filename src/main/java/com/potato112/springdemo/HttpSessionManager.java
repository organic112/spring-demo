package com.potato112.springdemo;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Spring Principal Manager
 */
@Service
public class HttpSessionManager {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    public String getHttpSessionId(){
        HttpSession session = httpSessionFactory.getObject();
        return session.getId();
    }

}
