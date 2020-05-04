package com.potato112.springdemo.web.service.security;


import com.potato112.springdemo.web.service.security.model.UserAuthorityDto;
import com.potato112.springdemo.web.ui.login.LoginView;
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

    public List<UserAuthorityDto> getAuthorities() {

        Authentication userAuth = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuth.getAuthorities();
        return (List<UserAuthorityDto>) authorities;
    }

    public Optional<UserAuthorityDto> getGrantedAuthorityByViewName(String viewName){

        List<UserAuthorityDto> groupsAuthorities =   getAuthorities();
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
