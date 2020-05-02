package com.potato112.springdemo.web.ui.common;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;

public class SysGridHelper {

    private SysGridHelper() {
    }

    public static void initializeGridStyle(Grid<?> grid) {

        grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        //grid.removeColumnByKey("id");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setWidth("2000px");
    }
}
