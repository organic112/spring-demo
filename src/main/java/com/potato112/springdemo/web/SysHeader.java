package com.potato112.springdemo.web;

import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UserDetailsDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@CssImport("./styles/shared-styles.css")
//@CssImport("./frontend/styles/shared-styles.css")
public class SysHeader extends Div {


    public SysHeader(UserAuthService userAuthService, WebSecurityService webSecurityService) {
        this.setWidthFull();
        this.setId("sys-header");

        Label systemName = new Label("DIOGLE SYS");

        UserDetailsAuthority userDetailsAuthority = webSecurityService.getUser();
        UserDetailsDto userDetailsDto = userDetailsAuthority.getUserDetailsDto();
        VaadinIcon userIcon = VaadinIcon.USER;
        VaadinIcon cog = VaadinIcon.COG;
        VaadinIcon enter = VaadinIcon.ENTER;
        VaadinIcon alarm = VaadinIcon.ALARM;
        VaadinIcon chart = VaadinIcon.CHART_LINE;

        VaadinIcon envelope = VaadinIcon.ENVELOPE;
        VaadinIcon download = VaadinIcon.DOWNLOAD;
        VaadinIcon calendar = VaadinIcon.CALENDAR;

        Component logoutButton = createLogoutButton(userAuthService, userDetailsDto.getEmail());
        HorizontalLayout icons = new HorizontalLayout(userIcon.create(), cog.create(),enter.create(), alarm.create(), chart.create(), envelope.create(), download.create(), calendar.create());

        HorizontalLayout horizontalLayout = new HorizontalLayout(systemName, userIcon.create(), new Text(userDetailsDto.getEmail()),icons ,logoutButton);
        add(horizontalLayout);
    }

    Button createLogoutButton(UserAuthService userAuthService, String labelText) {

        VaadinIcon userIcon = VaadinIcon.USER;
        Component component = VaadinIcon.USER.create();
        Button logoutButton = new Button(userIcon.create());
        logoutButton.setClassName("sys-button");

        logoutButton.setText(labelText);

        logoutButton.addClickListener(buttonClickEvent -> {
            userAuthService.invalidateUserSession();
        });
        return logoutButton;
    }


}
