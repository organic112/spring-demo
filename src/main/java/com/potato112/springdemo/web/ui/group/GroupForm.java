package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;
import com.potato112.springdemo.web.ui.common.DefaultConfirmAction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.binder.Binder;

public class GroupForm extends Div {

    private final GroupPermissionsGridFactory groupPermissionsGridFactory;

    Grid<GroupPermissionDto> permissionsGrid;

    public GroupForm(Binder<GroupDto> binder, UserAuthorityVo authorityVo, DefaultConfirmAction<GroupDto, GroupDto> saveAction) {

        GroupBaseSection groupBaseSection = new GroupBaseSection(binder);
        groupPermissionsGridFactory = new GroupPermissionsGridFactory(binder.getBean(), authorityVo);

        permissionsGrid = groupPermissionsGridFactory.create();

        groupPermissionsGridFactory.setSaveAction(saveAction);


        Button addButton = groupPermissionsGridFactory.createAddGroupPermissionsButton();

        Div baseSectionContent = new Div(groupBaseSection);
        Div gridSection = new Div();
        Label gridLabel = new Label("List of group permissions");
        gridSection.add(gridLabel, permissionsGrid, addButton);

        this.add(baseSectionContent);
        this.add(gridSection);
    }

    public void resetGridIsChanged() {

        System.out.println("FIXME permissions grid reload !");

        // permissionsGrid.getDataProvider().refreshAll();
    }


    public boolean gridWasModified() {

        //TODO add logic here
        return true;
    }
}
