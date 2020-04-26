package com.potato112.springdemo.security.userauthsecurity;


import com.potato112.springdemo.security.userauthsecurity.model.UserGroupsAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Provides information from Security Context
 */
@Service
public class UserAuthService {

    public List<UserGroupsAuthority> getAuthorities() {

        Authentication userAuth = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuth.getAuthorities();
        return (List<UserGroupsAuthority>) authorities;
    }

    public String getUserName(){
        return getAuthentication().getName();
    }

    public Authentication getAuthentication() {
        final Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
        return userAuth;
    }
}
