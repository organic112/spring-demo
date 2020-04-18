package com.potato112.springdemo.web.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.data.event.SortEvent;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;
import java.util.stream.Stream;

public class SortingHelper<T> {

    public void loadSorting(Grid<T> grid, SortingKey sortingKey) {

        VaadinSession currentSession = getSession();
        Object formSession = currentSession.getAttribute(sortingKey.getSortingKey());
        if (formSession != null) {
            grid.sort((List<GridSortOrder<T>>) formSession);
        }
    }

    private VaadinSession getSession() {
        return UI.getCurrent().getSession();
    }

    public void clearAllSorters() {

        Stream.of(SortingKey.values()).forEach(sortingKey -> {
            VaadinSession currentSession = getSession();
            currentSession.setAttribute(sortingKey.getSortingKey(), null);
        });
    }

    public void setSortingInSession(SortEvent<Grid<T>, GridSortOrder<T>> event, SortingKey sortingKey) {

        VaadinSession currentSession = getSession();
        List<GridSortOrder<T>> sortOrder = event.getSortOrder();
        currentSession.setAttribute(sortingKey.getSortingKey(), sortOrder);
    }
}
