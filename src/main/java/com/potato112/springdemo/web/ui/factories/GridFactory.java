package com.potato112.springdemo.web.ui.factories;

import com.vaadin.flow.component.grid.Grid;

public interface GridFactory<T> {

    // dedicated to create and return Grid
    Grid<T> create();

    // dedicated to build grid columns
    // void buildColumns(Grid<T> userGrid); // should stay private

}
