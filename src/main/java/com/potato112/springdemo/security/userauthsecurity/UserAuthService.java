package com.potato112.springdemo.security.userauthsecurity;


import com.potato112.springdemo.security.userauthsecurity.model.UserAuthority;
import com.potato112.springdemo.web.LoginView;
import com.vaadin.flow.component.UI;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Provides information from Security Context
 */
@Service
public class UserAuthService {

    public List<UserAuthority> getAuthorities() {

        Authentication userAuth = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuth.getAuthorities();
        return (List<UserAuthority>) authorities;
    }

    public Optional<UserAuthority> getGrantedAuthorityByViewName(String viewName){

        List<UserAuthority> groupsAuthorities =   getAuthorities();
        return groupsAuthorities.stream()
                .filter(auth -> auth.getPermittedViewName().equals(viewName))
                .findFirst();
    }

    public String getUserName(){
        return getAuthentication().getName();
    }

    public Authentication getAuthentication() {
        final Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
        return userAuth;
    }

    /**
     * Logout operation
     */
    public void invalidateUserSession(){
        UI.getCurrent().getSession().getSession().invalidate();
        UI.getCurrent().navigate(LoginView.class);
        UI.getCurrent().getPage().reload();
    }
}
