package com.potato112.springdemo.web.ui.factories;

import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.function.Consumer;

public class CommonGridColumnFactory {

    private UserAuthorityVo userAuthorityVo;

    public CommonGridColumnFactory(UserAuthorityVo userAuthorityVo) {
        this.userAuthorityVo = userAuthorityVo;
    }

    public <T> Grid.Column<T> addRemoveItemColumn(Grid<T> grid, Consumer<T> deleteAction) {

        Grid.Column<T> column = grid.addComponentColumn(item -> {
            Icon removeIcon = new Icon(VaadinIcon.TRASH);
            removeIcon.addClickListener(event -> {
                deleteAction.accept(item);
            });
            return removeIcon;
        });

        SysGridHeaderFactory gridHeaderFactory = new SysGridHeaderFactory();
        column.setKey("removeIconColumn");
        column.setFlexGrow(0);
        column.setWidth("68px");
        column.setSortable(false);
        column.setHeader(gridHeaderFactory.createHeader("ACTION"));
        return column;
    }

    public <T> Grid.Column<T> addActionColumn(Grid<T> grid, Consumer<T> navigationAction) {

        return grid.addComponentColumn(item -> {

            Icon icon = new Icon(VaadinIcon.PENCIL);

            SysButtonFactory buttonFactory = new SysButtonFactory();
            Button editButton = buttonFactory.createEditButton(icon, () -> navigationAction.accept(item));

            editButton.setVisible(getUserEditPermission());

            editButton.setHeight("16px");
            return new Div(editButton);
        }).setFlexGrow(0)
                .setWidth("68px")
                .setHeader(new SysGridHeaderFactory().createActionsHeader("EDIT"));
    }

    boolean getUserEditPermission() {
        return userAuthorityVo.canUpdate();
    }

}
