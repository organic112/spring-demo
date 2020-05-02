package com.potato112.springdemo.web.ui.common;

import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;

public class SysDropdownFactory {


    public SysDropdownMenu createDropdown(String label) {

        SysButtonFactory buttonFactory = new SysButtonFactory();

        Button labelButton = buttonFactory.createPlainButton(label, VaadinIcon.CARET_DOWN.create());

        return new SysDropdownMenu(VaadinIcon.CARET_DOWN.create(), labelButton);
    }

    public Div createButtonsFroDropdownMenuItem(String label) {

        Div menuButton = new Div(new Text(label));
        return menuButton;
    }
}
