package com.potato112.springdemo.web;

import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;

import java.util.Objects;

@Secured({})
@Slf4j
@CssImport("./styles/shared-styles.css")
//@CssImport("./frontend/styles/shared-styles.css")
public class MainView extends VerticalLayout implements RouterLayout, PageConfigurator {

    private Div contentContainer = new Div();

    public MainView(UserAuthService userAuthService, WebSecurityService webSecurityService) {

        this.setSpacing(false);

        Component header = new SysHeader(userAuthService, webSecurityService);
        add(header);

        Label topLabel = new Label("MAIN VIEW LAYOUT, below routed content:");
        add(topLabel);

        HorizontalLayout sideMenu = new SysSideMenuLayout(webSecurityService);
        add(sideMenu);
        this.expand(sideMenu);

        Div mainContentWindow = new Div();

        Div div = new Div();
        div.add(contentContainer);

        mainContentWindow.add(div);
        sideMenu.add(mainContentWindow);
        sideMenu.expand(mainContentWindow);

        Label mainViewLayoutLabel = new Label("MAIN VIEW BOTTOM LABEL");
        Button logoutButton = createLogoutButton(userAuthService);
        add(mainViewLayoutLabel, logoutButton);
    }

    @Override
    public void showRouterLayoutContent(HasElement hasElement) {

        log.info("Echo02 show router content...");

        Objects.requireNonNull(hasElement);
        Objects.requireNonNull(hasElement.getElement());
        contentContainer.removeAll();
        contentContainer.getElement().appendChild(hasElement.getElement());
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {

        //new CommonPageConfiguratio().
        log.info("Echo05 configure page...");
    }

    Button createLogoutButton(UserAuthService userAuthService) {
        Button logoutButton = new Button("LOGOUT");
        logoutButton.setClassName("sys-button");

        logoutButton.addClickListener(buttonClickEvent -> {
            userAuthService.invalidateUserSession();
/*
            UI.getCurrent().getSession().getSession().invalidate();
            UI.getCurrent().navigate(LoginView.class);
            UI.getCurrent().getPage().reload();*/
        });
        return logoutButton;
    }
}
