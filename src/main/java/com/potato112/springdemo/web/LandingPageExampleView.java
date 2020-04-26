package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.authentication.SysView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.PWA;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;



@PWA(name = "Sys Custom", shortName = "SC")
@Route(value = LandingPageExampleView.ROUTE, layout = MainView.class)
@Secured(value = LandingPageExampleView.VIEW_NAME)
@RouteAlias(value = "", layout = MainView.class)
@AllArgsConstructor
public class LandingPageExampleView extends SysPage implements BeforeEnterObserver {

    public static final String ROUTE = "";
    public static final String VIEW_NAME = "OTHER"; // not registered view_name - no access

    @PostConstruct
    public void init() {
        Div content = new Div();
        Button button = new Button("Loading page button");
        button.setIcon(VaadinIcon.USER.create());
        Label label = new Label("Landing Page example");

        button.addClickListener((ComponentEventListener<ClickEvent<Button>>)
                buttonClickEvent -> System.out.println("button clicked"));

        VerticalLayout layout = new VerticalLayout();
        layout.add(label);
        layout.add(button);

        content.add(layout);

        this.setContent(content);
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        System.out.println("Landing page BEFORE ENTER event" + beforeEnterEvent.getLocation().getPath());

    }
}
