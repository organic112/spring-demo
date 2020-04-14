package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
public class LoginView extends AbstractLoginView implements BeforeEnterObserver {

    public static final String ROUTE = "login" ;
    private LoginForm loginForm;


    private final WebSecurityService webSecurityService;

    public LoginView(WebSecurityService webSecurityService) {
        this.webSecurityService = webSecurityService;
    }

    @Override
    Component buildMainComponent() {

        log.info("Echo01 LoginView build component");

        loginForm = new LoginForm();
        LoginI18n loginMessages = getLoginI18n();

        loginForm.setI18n(loginMessages);
        loginForm.setAction("login");
        loginForm.addForgotPasswordListener(getForgotPasswordEventComponentEventListener());

        return loginForm;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

/*        if(beforeEnterEvent.getNavigationTarget()!=null) {
            log.info("LL01 before enter event" + beforeEnterEvent.getForwardTarget().toString());
        }*/

        if (webSecurityService.isUserLoggedIn()) {
            log.info("LL01 User logged in reroute to initial site...");
            beforeEnterEvent.forwardTo(OverviewFooView.class);  // FIXME
        }

        Location location = beforeEnterEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        log.info("LL01 query path: "+ location.getPathWithQueryParameters());

        Map<String, List<String>> parameters = queryParameters.getParameters();

        List<String> errors = parameters.getOrDefault("error", Collections.emptyList());
        if (!errors.isEmpty()) {
            loginForm.setError(true);
        }
    }

    private ComponentEventListener<AbstractLogin.ForgotPasswordEvent> getForgotPasswordEventComponentEventListener() {
        return event -> getUI().ifPresent(ui -> ui.getPage().setLocation(ForgotPasswordView.ROUTE));
    }

    private LoginI18n getLoginI18n() {

        LoginI18n loginMessages = new LoginI18n();

        LoginI18n.Header header = new LoginI18n.Header();
        header.setTitle("Sys login title");
        loginMessages.setHeader(header);

        LoginI18n.Form loginForm = new LoginI18n.Form();
        loginForm.setTitle("Login title");
        loginForm.setUsername("Username");
        loginForm.setPassword("Password");
        loginForm.setSubmit("Login");
        loginForm.setForgotPassword("Forgot password");
        loginMessages.setForm(loginForm);

        LoginI18n.ErrorMessage errorMessage = new LoginI18n.ErrorMessage();
        errorMessage.setTitle("Invalid user name and password");
        errorMessage.setMessage("Please enter correct credentials");

        loginMessages.setErrorMessage(errorMessage);
        return loginMessages;
    }



}
