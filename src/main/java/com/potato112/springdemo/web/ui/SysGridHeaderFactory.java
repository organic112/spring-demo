package com.potato112.springdemo.web.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;


public class SysGridHeaderFactory {

    public Component createHeader(String label) {

        Span headerLabel = new Span(label.toUpperCase());
        return headerLabel;
    }

    public Component createActionsHeader(String header) {
        return createHeader(header);
    }
}