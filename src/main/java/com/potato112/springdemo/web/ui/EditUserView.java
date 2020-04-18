package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.web.SysPage;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.HasUrlParameter;

public class EditUserView extends SysPage implements HasUrlParameter<String>, BeforeLeaveObserver {

    public static final String ROUTE = "user/edit";


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

    }
}
