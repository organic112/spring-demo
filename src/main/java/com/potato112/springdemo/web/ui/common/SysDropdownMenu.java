package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.dom.Element;

public class SysDropdownMenu extends Div {

    private boolean opened = false;
    private Div menu;
    private SysDropdownOverlay overlay;

    public SysDropdownMenu(Icon icon) {
        this.add(icon);
        prepareMenu();
    }

    public SysDropdownMenu(Icon icon, Component label) {
        if (label != null) {
            this.add(new Div(label));
        }
        this.add(icon);
        prepareMenu();
    }

    public void addItem(Component item) {
        menu.add(item);
    }

    private Element getMenuElement() {
        return this.menu.getElement();
    }

    void close() {
        this.opened = false;
        overlay.close();
    }

    void open() {
        this.opened = true;
        overlay.open();
    }

    private void prepareMenu() {

        this.addClickListener(event -> this.toggle());

        menu = new Div();
        this.overlay = new SysDropdownOverlay(this);
        this.overlay.add(menu);
    }

    private void toggle() {
    }
}
