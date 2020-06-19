package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;

public class SysDropdownOverlay extends Div {

    private Div parentDiv;


    public SysDropdownOverlay(SysDropdownMenu triggerMenu, Div parentDiv) {

        this.parentDiv = parentDiv;
        this.setClassName("sys-dropdown-overlay");
        this.addClickListener(event -> triggerMenu.close());
    }

    void open() {
        parentDiv.add(this); // - default   // FIXME adds menu not to header, but main page
        this.setVisible(true);
    }

    void close() {

        //this.getElement().removeFromParent();  // - default
        this.getElement().setVisible(false);     // not to pass null to java script
    }
}
