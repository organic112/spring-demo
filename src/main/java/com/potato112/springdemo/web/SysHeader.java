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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@CssImport("./styles/shared-styles.css")
//@CssImport("./frontend/styles/shared-styles.css")
public class SysHeader extends Div {


    public SysHeader(UserAuthService userAuthService, WebSecurityService webSecurityService) {
       // this.setWidthFull();
        this.setClassName("sys-main-header");



        UserDetailsAuthority userDetailsAuthority = webSecurityService.getUser();
        UserDetailsDto userDetailsDto = userDetailsAuthority.getUserDetailsDto();
        VaadinIcon userIcon = VaadinIcon.USER;
        VaadinIcon cog = VaadinIcon.COG;
        VaadinIcon signal = VaadinIcon.SIGNAL;
        VaadinIcon alarm = VaadinIcon.ALARM;
        VaadinIcon chart = VaadinIcon.CHART_LINE;

        VaadinIcon envelope = VaadinIcon.ENVELOPE;
        VaadinIcon download = VaadinIcon.DOWNLOAD;
        VaadinIcon calendar = VaadinIcon.CALENDAR;

        Button configBtn = new Button("Settings", cog.create());
        configBtn.setClassName("sys-button");
        Button alarmBtn = new Button("Alerts", alarm.create());
        alarmBtn.setClassName("sys-button");
        Button chartBtn = new Button("Trends", chart.create());
        chartBtn.setClassName("sys-button");
        Button envelopeBtn = new Button("Notification", envelope.create());
        envelopeBtn.setClassName("sys-button");
        Button downloadBtn = new Button("Download", download.create());
        downloadBtn.setClassName("sys-button");
        Button calendarBtn = new Button("Events", calendar.create());
        calendarBtn.setClassName("sys-button");

        Button signalBtn = new Button("Signals", signal.create());
        signalBtn.setClassName("sys-button");

        Button userBtn = new Button(userDetailsDto.getEmail(), userIcon.create());
        userBtn.setClassName("sys-button");
        Label systemName = new Label("DIOGLE SYS");

        Component logoutButton = createLogoutButton(userAuthService);

        // CREATE MENU BAR
        HorizontalLayout leftSide = new HorizontalLayout(systemName);
        leftSide.setClassName("sys-logo");
        //leftSide.setSpacing(false);

        HorizontalLayout userMenuBar = new HorizontalLayout(downloadBtn, envelopeBtn, configBtn, userBtn, logoutButton);
        userMenuBar.setClassName("user-menu-bar");

        HorizontalLayout rightSide = new HorizontalLayout(userMenuBar);
        rightSide.setClassName("user-menu-right-side");
        //rightSide.setSpacing(false);
        HorizontalLayout userMenuBarHorizontalLayout = new HorizontalLayout(leftSide, rightSide);
        userMenuBarHorizontalLayout.setClassName("menu-top-container");

        // CREATE MAIN MENU

        HorizontalLayout horizontalMenuBar = new HorizontalLayout(chartBtn, calendarBtn, signalBtn, alarmBtn);
        horizontalMenuBar.setClassName("sys-menu-bar");

        HorizontalLayout horizontalMenuBarContainer = new HorizontalLayout();
        horizontalMenuBarContainer.add(horizontalMenuBar);
        horizontalMenuBarContainer.setClassName("menu-bottom-container");

        add(userMenuBarHorizontalLayout);
        add(horizontalMenuBarContainer);
    }

    Button createLogoutButton(UserAuthService userAuthService) {

        Button logoutButton = new Button();
        logoutButton.setClassName("sys-logout-button");
        logoutButton.setText("Logout");
        logoutButton.addClickListener(buttonClickEvent -> {
            userAuthService.invalidateUserSession();
        });
        return logoutButton;
    }


}
