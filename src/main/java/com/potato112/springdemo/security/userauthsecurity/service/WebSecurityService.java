package com.potato112.springdemo.security.userauthsecurity.service;


import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Handles security and query rights
 */
public class WebSecurityService {

    /**
     * Check if user is authenticated (logged in)
     * Note: AnonymousAuthenticationToken has to be ignored (it is always created)
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isLogged = authentication != null &&
                !(authentication instanceof AnonymousAuthenticationToken) &&
                authentication.isAuthenticated();

        return isLogged;
    }

    /**
     * Returns if user has access to:
     * - view class (defines access rules as role)
     */
    public boolean isAccessApproved(Class<?> securedViewClass) {

        Secured secured = AnnotationUtils.findAnnotation(securedViewClass, Secured.class);

        if (secured == null) {
            return true;
        }
        final List<String> allowedRoles = Arrays.asList(secured.value());
        return isAccessApproved(allowedRoles);
    }

    public boolean isAccessApproved(List<String> allowedRoles) {

        final Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuth.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(allowedRoles::contains);
    }

    public UserDetailsAuthority getUser() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        final Authentication userAuth = securityContext.getAuthentication();
        return (UserDetailsAuthority) userAuth.getPrincipal();
    }
}
