package com.potato112.springdemo.web.ui.landing;

import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UserDetailsDto;
import com.potato112.springdemo.web.ui.common.SysDropdownMenu;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.user.UserOverview;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

    private UserAuthService userAuthService;
    private WebSecurityService webSecurityService;

    @PostConstruct
    public void init() {
        Div pageContent = new Div();
        Label landingPage = new Label("Default Landing Page for authorized users");
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(getExampleButton());
        verticalLayout.add(getLinksButton());
        verticalLayout.add(landingPage);
        pageContent.add(verticalLayout);
        pageContent.add(createDropDownMenu());
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

    private Component createDropDownMenu() {

        UserDetailsAuthority userDetailsAuthority = webSecurityService.getUser();
        UserDetailsDto userDetailsDto = userDetailsAuthority.getUserDetailsDto();
        VaadinIcon arrowIcon = VaadinIcon.CHEVRON_DOWN_SMALL;
        VaadinIcon userIcon = VaadinIcon.USER;
        HorizontalLayout horizontalLayout = new HorizontalLayout(userIcon.create(), new Text(userDetailsDto.getEmail()));
        Div labelContainer = new Div(horizontalLayout);
        SysDropdownMenu dropdownMenu = new SysDropdownMenu(arrowIcon.create(), labelContainer, this);
        Component userProfileButton = createAccountDropdownMenItem("User profile", this::navigateToUserProfile);
        dropdownMenu.addItem(userProfileButton);
        Component logoutButton = createLogoutButton(userAuthService);
        dropdownMenu.addItem(logoutButton);
        return dropdownMenu;
    }

    Button createLogoutButton(UserAuthService userAuthService) {
        Button logoutButton = new Button("LOGOUT");
        logoutButton.addClickListener(buttonClickEvent -> {
            userAuthService.invalidateUserSession();
/*
            UI.getCurrent().getSession().getSession().invalidate();
            UI.getCurrent().navigate(LoginView.class);
            UI.getCurrent().getPage().reload();*/
        });
        return logoutButton;
    }

    private Component initOrganizationSelect(WebSecurityService webSecurityService) {
        Label label = new Label("Company label");
        return label;
    }

    private Component createAccountDropdownMenItem(String text, Runnable action) {
        Div item = new Div(new Text(text));
        item.addClickListener(event -> action.run());
        return item;
    }

    private void navigateToUserProfile() {
        UI.getCurrent().navigate(UserOverview.class);
    }

    private Component initImage() {

        // FIXME add image

        Image image = new Image();

        Div leftImageWrapper = new Div();
        leftImageWrapper.add(new Span());
        return leftImageWrapper;
    }
}
