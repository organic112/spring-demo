package com.potato112.springdemo.security.userauthsecurity.service;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import org.springframework.stereotype.Service;


@Service
public class RestUserServiceImpl implements UserService {

    private final JSONUserClient jsonUserClient;
    private final WebSecurityService securityService;

    public RestUserServiceImpl(JSONUserClient jsonUserClient, WebSecurityService securityService) {
        this.jsonUserClient = jsonUserClient;
        this.securityService = securityService;
    }

    @Override
    public UserDetailsAuthority getUserByName(String userName) {

        return this.jsonUserClient.getUserByName(userName);
    }
}
