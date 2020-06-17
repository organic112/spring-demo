package com.potato112.springdemo.web;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;

public class SideMenuItemFactory {

    Div buildItem(VaadinIcon icon, String text, Runnable clickListener){
        Div item = new Div();

        // FIXME
        // item.add(new Div(new SysIcon(icon, SysIcon.Variant.MEDIUM) ) );
        item.add(new Div (new Text(text)));
        item.addClickListener(event -> clickListener.run());
        return item;
    }
}
