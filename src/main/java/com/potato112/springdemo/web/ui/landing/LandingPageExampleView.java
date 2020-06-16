package com.potato112.springdemo.web.ui.landing;

import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;


@PWA(name = "Sys Custom", shortName = "SC")
@Route(value = LandingPageExampleView.ROUTE, layout = MainView.class)
@Secured(value = LandingPageExampleView.VIEW_NAME)
//@RouteAlias(value = "", layout = MainView.class)
@AllArgsConstructor
public class LandingPageExampleView extends SysPage implements BeforeEnterObserver {

    public static final String ROUTE = "";
    public static final String VIEW_NAME = "DEFAULT_AUTHORIZED_VIEW";  //"OTHER"; // not registered view_name - no access

    @PostConstruct
    public void init() {
        Div pageContent = new Div();
        Label landingPage = new Label("Default Landing Page for authorized users");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(getExampleButton());
        verticalLayout.add(getLinksButton());
        verticalLayout.add(landingPage);
        pageContent.add(verticalLayout);
        this.setContent(pageContent);
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        System.out.println("Landing page BEFORE ENTER event" + beforeEnterEvent.getLocation().getPath());
    }

    private Button getExampleButton(){
        Button button = new Button("Loading page button");
        button.setId("LoadingPageButton_id");
        button.setIcon(VaadinIcon.USER.create());
        button.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> System.out.println("button clicked"));
        return button;
    }

    private Button getLinksButton(){
        Button button = new Button("Redirect to sys links");
        button.setId("RedirectToLinks_id");
        button.setIcon(VaadinIcon.USER.create());
        button.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            getUI().ifPresent(ui -> ui.getPage().setLocation("not_existing_page"));
        });
        return button;
    }
}
