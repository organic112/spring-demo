package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.Collection;

public class SysUtilActionBar extends Div {

    private Collection<Component> items = new ArrayList<>();

    public SysUtilActionBar() {
    }

    public void addItem(Component component) {
        items.add(component);
        this.add(component);
    }
}
