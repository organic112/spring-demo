/*package com.potato112.springdemo.web;


import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = DefaultView.ROUTE)
public class DefaultView extends Div implements BeforeEnterObserver {

    public static final String ROUTE = "";

    private WebSecurityService webSecurityService;

    public DefaultView(WebSecurityService webSecurityService) {
        this.webSecurityService = webSecurityService;
        setText("default page");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        log.info("DEFALUT VIEW entered...");

        if (webSecurityService.isUserLoggedIn()) {
            beforeEnterEvent.forwardTo(LandingPageExampleView.class);
        } else {
            beforeEnterEvent.forwardTo(LoginView.class);
        }
    }
}*/
