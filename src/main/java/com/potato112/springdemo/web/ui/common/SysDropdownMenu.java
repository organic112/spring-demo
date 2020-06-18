package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.dom.Element;

public class SysDropdownMenu extends Div {

    private boolean menuIsOpened = false;
    private Div menuDiv;
    private SysDropdownOverlay menuOverlay;

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
        menuDiv.add(item);
    }

    private Element getMenuElement() {
        return this.menuDiv.getElement();
    }

    void close() {
        this.menuIsOpened = false;
        menuOverlay.close();
        menuDiv.setClassName("opened", false);
    }

    void open() {
        this.menuIsOpened = true;
        menuOverlay.open();
        menuDiv.setClassName("opened", true);
    }

    private void prepareMenu() {

        this.addClickListener(event -> this.toggle());

        menuDiv = new Div();
        menuDiv.setClassName("sys-dropdown-menu");

        this.menuOverlay = new SysDropdownOverlay(this);
        this.menuOverlay.add(menuDiv);
    }

    private void toggle() {

        this.menuIsOpened = !this.menuIsOpened;

        // not necessary to open/close on UI
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

        if (this.menuIsOpened) {
            open();
        } else {
            close();
        }
    }
}
