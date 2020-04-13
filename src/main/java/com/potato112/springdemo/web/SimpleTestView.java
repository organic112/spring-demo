package com.potato112.springdemo.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;

//@PWA(name = "Sys Custom", shortName = "SC")
@Route(value = "", layout = MainView.class)
@AllArgsConstructor
public class SimpleTestView extends SysPage {

    @PostConstruct
    public void init() {
        Button button = new Button("Simple test view button");

        this.setContent(button);
    }
}
