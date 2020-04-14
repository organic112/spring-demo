package com.potato112.springdemo.security.userauthsecurity.service;


import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.vaadin.flow.server.ServletHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Handles security and query rights
 */

@Slf4j
@Service
public class WebSecurityService {

    /**
     * Check if user is authenticated (logged in)
     * Note: AnonymousAuthenticationToken has to be ignored (it is always created)
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isLogged = null != authentication &&
                !(authentication instanceof AnonymousAuthenticationToken) &&
                authentication.isAuthenticated();

        if (null == authentication) {
            log.info("authentiaction is null");
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            log.info("!! Anonymous token in authentication!");
        } else {
            log.info("OK, not anonymus tocken in auth");
        }
        log.info("Is authenticated: " + authentication.isAuthenticated());
        log.info("Echo01 Is user logged in: " + isLogged);

        // FIXME (user not authenticated
        return isLogged;

        // return true;
    }

    public static boolean isFrameworkInternalRequest(HttpServletRequest request) {

        final String parameterValue = request.getParameter("v-r");

        boolean isFrameworkInternalRequet =
                parameterValue != null
                        && Stream.of(ServletHelper.RequestType.values())
                        .anyMatch(r -> r.getIdentifier().equals(parameterValue));

        log.info("Echo02 Check is framework internal request:" + isFrameworkInternalRequet + " parameter_value: " + parameterValue);

        return isFrameworkInternalRequet;
    }

    /**
     * Returns if user has access to:
     * - view class (defines access rules as role)
     */
    public boolean isAccessGranted(Class<?> securedViewClass) {

        Secured secured = AnnotationUtils.findAnnotation(securedViewClass, Secured.class);

        if (secured == null) {
            return true;
        }
        final List<String> allowedRoles = Arrays.asList(secured.value());

        boolean isAccessGranted = isAccessGranted(allowedRoles);

        log.info("Echo02 Check is framework internal request:" + isAccessGranted);
        return isAccessGranted;
    }

    public boolean isAccessGranted(List<String> allowedRoles) {

        final Authentication userAuth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuth.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(allowedRoles::contains);
    }

    public UserDetailsAuthority getUser() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        final Authentication userAuth = securityContext.getAuthentication();
        return (UserDetailsAuthority) userAuth.getPrincipal();
    }

    public void setAuthToken(UserDetailsAuthority userDetailsAuthority) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication userAuthentication = securityContext.getAuthentication();
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetailsAuthority, userAuthentication.getCredentials(),
                        userDetailsAuthority.getAuthorities());

        securityContext.setAuthentication(newAuth);
    }


}
