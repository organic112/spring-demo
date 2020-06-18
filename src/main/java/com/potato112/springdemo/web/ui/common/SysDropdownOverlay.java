package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;

public class SysDropdownOverlay extends Div {


    public SysDropdownOverlay(SysDropdownMenu triggerMenu) {

        this.setClassName("sys-dropdown-overlay");
        this.addClickListener(event -> triggerMenu.close());
    }

    void open() {
        UI.getCurrent().add(this); // - default   // FIXME adds menu not to header, but main page
        this.setVisible(true);
    }

    void close() {

        //this.getElement().removeFromParent();  // - default
        this.getElement().setVisible(false);     // not to pass null to java script
    }
}
