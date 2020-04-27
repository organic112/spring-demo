package com.potato112.springdemo.web.ui.factories;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;

import java.util.Collection;

public class SysGridCellListItemFactory {


    private SysGridCellListItemFactory() {
    }

    public static Component create(Collection<String> elements) {

        UnorderedList item = new UnorderedList();
        item.setClassName("grid_cell_list");

        for (String element : elements) {
            ListItem listItem = new ListItem(element);
            item.add(listItem);
        }
        return item;
    }


}
