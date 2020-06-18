package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;

public class SysDropdownOverlay extends Div {


    public SysDropdownOverlay(SysDropdownMenu triggerMenu ) {

        this.addClickListener( event -> triggerMenu.close());
    }

    void open(){
        UI.getCurrent().add(this);
    }

/*    void open(){
        //this.add(this);
    }*/
    void close(){
        this.getElement().removeFromParent();
    }
}
