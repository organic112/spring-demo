package com.potato112.springdemo.web;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class SideMenuNavigationTabFactory {

    private final SideMenuItemFactory itemFactory;

    Tab buildItemNavigationLink(SideMenuNavigationLink linkEntry){

        String label = linkEntry.getLabel();
        Div sideMenuItem = itemFactory.buildItem(linkEntry.getIcon(), label, () -> UI.getCurrent().navigate(linkEntry.getNavigationTarget()));
        Tab tab = new Tab();
        tab.add(sideMenuItem);
        String linkUiid = linkEntry.getUuid();
        tab.setId(linkUiid);
        return tab;
    }
}
