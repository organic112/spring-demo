package com.potato112.springdemo.web;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class SysDividerFactory {


    public Component createHorizontalLineDivider(){
        Div sysDivider = prepareDivider();
        return sysDivider;
    }

    private Div prepareDivider() {
        Div sysDivider = new Div();
        return sysDivider;
    }
}
