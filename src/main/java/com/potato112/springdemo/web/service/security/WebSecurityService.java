package com.potato112.springdemo.web.service.security;


import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import lombok.AllArgsConstructor;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Handles security and query rights
 */
@Slf4j
@Service
@AllArgsConstructor
public class WebSecurityService {

    private static int MAX_NUMBER_SECURITY_NAMES_PER_VIEW = 1;

    private UserAuthService userAuthService;

    /**
     * Check if user is authenticated (logged in)
     * Note: AnonymousAuthenticationToken has to be ignored (it is always created)
     */
    public boolean isUserLoggedIn() {
        Authentication authentication = userAuthService.getAuthentication();

        boolean isLogged = null != authentication &&
                !(authentication instanceof AnonymousAuthenticationToken) &&
                authentication.isAuthenticated();

        log.info("Echo01 Is user logged in: " + isLogged);
        return isLogged;
    }

    public static boolean isFrameworkInternalRequest(HttpServletRequest request) {

        final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);

        boolean isFrameworkInternalRequest =
                parameterValue != null
                        && Stream.of(ServletHelper.RequestType.values())
                        .anyMatch(r -> r.getIdentifier().equals(parameterValue));

        log.info("Echo02 URL:" + request.getRequestURI() +
                " path info: " + request.getPathInfo() +
                " servlet path: " + request.getServletPath() +
                " Check is framework internal request:" + String.valueOf(isFrameworkInternalRequest).toUpperCase()
                + " parameter_value: " + parameterValue);

        return isFrameworkInternalRequest;
    }

    /**
     * Returns if user has access to:
     * - view class (defines access rules as role)
     */
    public boolean isAccessGranted(Class<?> securedViewTargetClass) {

        Secured secured = AnnotationUtils.findAnnotation(securedViewTargetClass, Secured.class);

        if (secured == null) {
            return true;
        }

        final List<String> securedPageViewNames = Arrays.asList(secured.value());
        validateSecurityTag(securedPageViewNames);

        boolean isAccessGranted = isAccessGranted(securedPageViewNames);

        log.info("Echo02 Check is framework internal request:" + isAccessGranted);
        return isAccessGranted;
    }

    /**
     * @Secured annotation on secured Page view should contain no more than 1 security tag.
     * (this is
     */
    private void validateSecurityTag(List<String> values) {
        if (values.size() > MAX_NUMBER_SECURITY_NAMES_PER_VIEW) {
            throw new IllegalArgumentException("Security annotation contains more than 1 security tag.");
        }
    }

    public boolean isAccessGranted(List<String> securedPageViewNames) {

        final Authentication userAuth = userAuthService.getAuthentication();

        if (null != userAuth) {
            log.info("AA01 user auth: " + userAuth.getName());

            Collection<? extends GrantedAuthority> userGrantedAuthorities = userAuth.getAuthorities();
            return userGrantedAuthorities.stream().map(GrantedAuthority::getAuthority).anyMatch(securedPageViewNames::contains);

        } else {
            log.info("AA02 user auth is null");
        }
        return false;
    }

    public UserDetailsAuthority getUser() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        final Authentication userAuth = securityContext.getAuthentication();
        return (UserDetailsAuthority) userAuth.getPrincipal();
    }

    public void setNewAuthentication() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        final Authentication userAuth = securityContext.getAuthentication();
        UserDetailsAuthority userDetailsAuthority = (UserDetailsAuthority) userAuth.getPrincipal();

        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetailsAuthority, userAuth.getCredentials(),
                        userDetailsAuthority.getAuthorities());

        securityContext.setAuthentication(newAuth);

        // check new authentication
        SecurityContext updatedSecurityContext = SecurityContextHolder.getContext();
        List<GrantedAuthority> authorities = new ArrayList<>(updatedSecurityContext.getAuthentication().getAuthorities());

        log.info("A003 new authorities from updated context number: " + authorities.size());
    }

    public void setNewAuthentication(UserDetailsAuthority userDetailsAuthority) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication userAuthentication = securityContext.getAuthentication();
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetailsAuthority, userAuthentication.getCredentials(),
                        userDetailsAuthority.getAuthorities());

        securityContext.setAuthentication(newAuth);
    }
}
