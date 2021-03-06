package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = ForgotPasswordView.ROUTE)
public class ForgotPasswordView extends AbstractLoginView implements BeforeEnterObserver {

    private final WebSecurityService webSecurityService;
    public static final String ROUTE = "forgot-password";

    // TODO implement UserService
    // private static final UserService userService;

    public ForgotPasswordView(WebSecurityService webSecurityService) {
        //this.userService = userService;
        this.webSecurityService = webSecurityService;
    }

    Component buildMainComponent() {

        Label forgotPassLabel = new Label("Need restore password?");
        Label recoveryLinkLabel = new Label("recovery link will be send to ");
        TextField userNameTextField = new TextField("Username");
        userNameTextField.getElement().setAttribute("name", "username");
        userNameTextField.setRequired(true);
        //userNameTextField.setClassName("front-page-textfield");

        Button submitButton = new Button("Send recovery link");
        EmailValidator emailValidator = new EmailValidator("wrong e-mail address");

        Binder<String> binder = new Binder<>();
        binder.forField(userNameTextField).withValidator(emailValidator).bind(string -> string, (s, s2) -> {
        });
        submitButton.addClickListener(buttonClickEvent -> {

            boolean error = emailValidator.apply(userNameTextField.getValue(), null).isError();

            if (userNameTextField.isEmpty() || error) {
                userNameTextField.setInvalid(true);
            } else {
                // userservice.resetPassword(userNameTextField.getValue());
                getUI().ifPresent(ui -> ui.navigate(LoginView.ROUTE));
                // todo Show notification
            }
        });

        Anchor returnToLogin = new Anchor();
        returnToLogin.setText("Return to login page");
        returnToLogin.getElement().addEventListener("click", e -> getUI().ifPresent(ui -> ui.navigate(LoginView.ROUTE)));

        HorizontalLayout anchorLayout = new HorizontalLayout(returnToLogin);
        anchorLayout.setWidthFull();
        anchorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.add(userNameTextField, submitButton);

        VerticalLayout mainLayout = new VerticalLayout(forgotPassLabel, recoveryLinkLabel, formLayout, anchorLayout);
        return mainLayout;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (webSecurityService.isUserLoggedIn()) {
            beforeEnterEvent.forwardTo(LandingPageExampleView.class);
        }
    }


}
