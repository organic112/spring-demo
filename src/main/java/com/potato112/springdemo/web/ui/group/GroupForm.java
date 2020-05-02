package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;

public class GroupForm extends Div {

    private final GroupPermissionsGridWrapper groupPermissionsGridWrapper;


    public GroupForm(Binder<GroupDto> binder) {

        GroupBaseSection groupBaseSection = new GroupBaseSection(binder);
        groupPermissionsGridWrapper = new GroupPermissionsGridWrapper(binder.getBean());

        Grid<GroupPermissionDto> permissionsGrid = groupPermissionsGridWrapper.create();
        Button addButton = groupPermissionsGridWrapper.createAddGroupPermissionsButton();

        Div baseSectionContent = new Div(groupBaseSection);
        Div gridSection = new Div();
        gridSection.add(permissionsGrid, addButton);

        this.add(baseSectionContent);
        this.add(gridSection);
    }

    public void resetGridIsChanged(){

        System.out.println("FIXME implement grid reset !");
    }



    public boolean gridWasModified(){

        //TODO add logic here
        return true;
    }
}
