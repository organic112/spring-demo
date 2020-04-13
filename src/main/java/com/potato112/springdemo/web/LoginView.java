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
@Route(value = "login")
@PageTitle("Login")
public class LoginView extends AbstractLoginView implements BeforeEnterObserver {

    private LoginForm login;

    private final WebSecurityService webSecurityService;

    public LoginView(WebSecurityService webSecurityService) {
        this.webSecurityService = webSecurityService;
    }

    @Override
    Component buildMainComponent() {

        log.info("Echo01 LoginView build component");

        login = new LoginForm();
        LoginI18n loginMessages = getLoginI18n();

        login.setI18n(loginMessages);
        login.setAction("login");

        login.addForgotPasswordListener(getForgotPasswordEventComponentEventListener());
        return login;
    }

    private ComponentEventListener<AbstractLogin.ForgotPasswordEvent> getForgotPasswordEventComponentEventListener() {
        return event -> getUI().ifPresent(ui -> ui.getPage().setLocation("forgot-password"));
    }

    private LoginI18n getLoginI18n() {

        LoginI18n loginMessages = new LoginI18n();
        LoginI18n.Header header = new LoginI18n.Header();
        loginMessages.setHeader(header);

        LoginI18n.Form form = new LoginI18n.Form();
        form.setTitle("login");
        form.setUsername("Username");
        form.setPassword("Password");
        form.setSubmit("Login");
        form.setForgotPassword("Forgot password");
        loginMessages.setForm(form);

        LoginI18n.ErrorMessage errorMessage = new LoginI18n.ErrorMessage();
        errorMessage.setTitle("Invalid user name and password");
        errorMessage.setMessage("Please enter correct credentials");
        loginMessages.setErrorMessage(errorMessage);
        return loginMessages;
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

/*        if(beforeEnterEvent.getNavigationTarget()!=null) {
            log.info("LL01 before enter event" + beforeEnterEvent.getForwardTarget().toString());
        }*/

        if (webSecurityService.isUserLoggedIn()) {

            log.info("LL01 User logged in reroute to initial site...");
            beforeEnterEvent.forwardTo(OverviewFooView.class);
        }
        Location location = beforeEnterEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();

        log.info("LL01 query path: "+ location.getPathWithQueryParameters());

        Map<String, List<String>> parameters = queryParameters.getParameters();

        List<String> errors = parameters.getOrDefault("error", Collections.emptyList());
        if (!errors.isEmpty()) {
            login.setError(true);
        }
    }
}
