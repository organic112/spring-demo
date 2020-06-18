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
        this.setClassName("sys-dropdown");
        this.add(icon);
        prepareMenu();
    }

    public SysDropdownMenu(Icon icon, Component label) {
        this.setClassName("sys-dropdown");
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
        menu.setClassName("opened", false);
    }

    void open() {
        this.opened = true;
        overlay.open();
        menu.setClassName("opened", true);
    }

    private void prepareMenu() {

        this.addClickListener(event -> this.toggle());

        menu = new Div();
        menu.setClassName("sys-dropdown-menu");
        this.overlay = new SysDropdownOverlay(this);
        this.overlay.add(menu);
    }

    private void toggle() {

        this.opened = !this.opened;
        String expression =
                "var rectAnchor = $0.getBoundingClientRect();" +
                "var rectMenu = $1.getBoundingClientRect();" +
                "var topShift = (rectAnchor.top + rectAnchor.height);" +
                "if(topShift + rectMenu.height > window.innerHeight){" +
                "$1.style.top = (topShift - (rectAnchor.height + 8) - rectMenu.height) + 'px';" +
                "} else {" +
                "$1.style.top = topShift + 'px';" +
                "}" +
                "$1.style.left = (rectAnchor.right - rectMenu.width) + 'px';" +
                "";
        this.getElement().executeJs(expression, this.getElement(), getMenuElement());
        if (this.opened) {
            open();
        } else {
            close();
        }
    }
}
