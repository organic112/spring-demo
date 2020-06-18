package com.potato112.springdemo.web;

import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UserDetailsDto;
import com.potato112.springdemo.web.ui.common.SysDropdownMenu;
import com.potato112.springdemo.web.ui.user.UserOverview;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SysHeader extends Div {


    public SysHeader(UserAuthService userAuthService, WebSecurityService webSecurityService) {
        this.setWidthFull();
        this.setId("sys-header");

/*        HorizontalLayout leftPartWrap = new HorizontalLayout();
        leftPartWrap.add(initImage());
        leftPartWrap.add(initOrganizationSelect(webSecurityService));*/
        //add(leftPartWrap);

        HorizontalLayout rightPartWrap = new HorizontalLayout();
        rightPartWrap.add(initAccDropdownMenu(userAuthService, webSecurityService));
        add(rightPartWrap);
    }

    private Component initAccDropdownMenu(UserAuthService userAuthService, WebSecurityService webSecurityService) {

        UserDetailsAuthority userDetailsAuthority = webSecurityService.getUser();
        UserDetailsDto userDetailsDto = userDetailsAuthority.getUserDetailsDto();

        VaadinIcon arrowIcon = VaadinIcon.CHEVRON_DOWN_SMALL;
        VaadinIcon userIcon = VaadinIcon.USER;

        Div userIconContainer = new Div(userIcon.create());
        Div label = new Div( new Text(userDetailsDto.getEmail()));
        Div labelContainer = new Div(userIconContainer, label);

        SysDropdownMenu dropdownMenu = new SysDropdownMenu(arrowIcon.create(), labelContainer);

        Component userProfileButton = createAccountDropdownMenItem("User profile", this::navigateToUserProfile);
        dropdownMenu.addItem(userProfileButton);

        Component logoutButton = createLogoutButton(userAuthService);
        dropdownMenu.addItem(logoutButton);

        return dropdownMenu;
    }

    Button createLogoutButton(UserAuthService userAuthService ){
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

    private Component createAccountDropdownMenItem(String text, Runnable action){
        Div item = new Div(new Text(text));
        item.addClickListener(event-> action.run());
        return item;
    }

    private void navigateToUserProfile(){
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
