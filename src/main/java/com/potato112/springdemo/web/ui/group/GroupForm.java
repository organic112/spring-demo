package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.web.service.group.model.GroupDto;
import com.potato112.springdemo.web.service.group.model.GroupPermissionDto;
import com.potato112.springdemo.web.service.security.model.UserAuthorityDto;
import com.potato112.springdemo.web.ui.common.action.DefaultConfirmAction;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.binder.Binder;

public class GroupForm extends Div {

    public GroupForm(Binder<GroupDto> binder, UserAuthorityDto authorityVo, DefaultConfirmAction<GroupDto, GroupDto> saveAction) {

        final GroupBaseSection groupBaseSection = new GroupBaseSection(binder);
        final GroupPermissionsGridFactory groupPermissionsGridFactory = new GroupPermissionsGridFactory(binder.getBean(), authorityVo);
        final Grid<GroupPermissionDto> permissionsGrid = groupPermissionsGridFactory.create();

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
        return true;  //TODO add logic here
    }
}
