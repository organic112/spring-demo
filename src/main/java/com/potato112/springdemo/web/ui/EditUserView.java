package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.SysPage;
import com.vaadin.flow.router.*;

@Route(value = EditUserView.ROUTE, layout = MainView.class)
public class EditUserView extends SysPage implements HasUrlParameter<String>, BeforeLeaveObserver {

    public static final String ROUTE = "user/edit";

    // private UserService userService;


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

        // UserVo userVo = userService.getUser(s).o

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

    }
}
