package com.potato112.springdemo.web.form;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UnsupportedBrowserView extends VerticalLayout {


    public UnsupportedBrowserView() {
        add(new Label("unsupported browser"));
    }
}
