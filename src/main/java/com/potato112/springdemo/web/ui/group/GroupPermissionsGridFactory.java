package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.web.service.group.model.GroupDto;
import com.potato112.springdemo.web.service.group.model.GroupPermissionDto;
import com.potato112.springdemo.web.service.security.model.UserAuthorityDto;
import com.potato112.springdemo.web.ui.common.action.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.common.dialogs.LookupWindow;
import com.potato112.springdemo.web.ui.common.grid.SysGridHelper;
import com.potato112.springdemo.web.ui.factories.CommonGridColumnFactory;
import com.potato112.springdemo.web.ui.factories.GridFactory;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.ui.factories.SysGridHeaderFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;

import java.util.List;

public class GroupPermissionsGridFactory implements GridFactory<GroupPermissionDto> {

    private Grid<GroupPermissionDto> groupPermissionsGrid;
    // private BinderWithValueChangeListener<GroupPermissionDto> groupPermissionBinder;
    private UserAuthorityDto authorityVo;

    private Button addButton;
    private Button saveButton;

    private boolean gridIsChanged = false;

    private LookupWindow lookupWindow;

    private DefaultConfirmAction<GroupDto, GroupDto> saveAction;

    /**
     * Wraps grid of current group permission
     */
    public GroupPermissionsGridFactory(GroupDto bean, UserAuthorityDto authorityVo) {

        // this.confirmUpdateEvent = confirmUpdateEvent;
        this.authorityVo = authorityVo;

        if (null == bean) {
            throw new IllegalArgumentException("Group form is not initialized. Bean is null");
        }
        this.groupPermissionsGrid = new Grid<>(GroupPermissionDto.class);
        SysGridHelper.initializeGridStyle(groupPermissionsGrid);


        List<GroupPermissionDto> groupPermissions = bean.getGroupPermissions();
        System.out.println("Group permissions size: " + groupPermissions.size());

        this.groupPermissionsGrid.setItems(groupPermissions);
    }

    public Grid<GroupPermissionDto> create() {

        SysGridHeaderFactory headerFactory = new SysGridHeaderFactory();
        groupPermissionsGrid.setColumns();

        Grid.Column<GroupPermissionDto> viewNameColumn = groupPermissionsGrid.addColumn("viewName");
        viewNameColumn.setHeader(headerFactory.createHeader("View Name"));

        Grid.Column<GroupPermissionDto> canCreateColumn = groupPermissionsGrid.addColumn("canCreate");
        canCreateColumn.setHeader(headerFactory.createHeader("Create"));

        Grid.Column<GroupPermissionDto> canUpdateColumn = groupPermissionsGrid.addColumn("canUpdate");
        canUpdateColumn.setHeader(headerFactory.createHeader("Update"));

        Grid.Column<GroupPermissionDto> canDeleteColumn = groupPermissionsGrid.addColumn("canDelete");
        canDeleteColumn.setHeader(headerFactory.createHeader("Delete"));

        Grid.Column<GroupPermissionDto> actionColumn = configureActionColumn();

        groupPermissionsGrid.setColumnOrder(viewNameColumn, canCreateColumn, canUpdateColumn, canDeleteColumn, actionColumn);

        return groupPermissionsGrid;
    }

    private Grid.Column<GroupPermissionDto> configureActionColumn() {

        CommonGridColumnFactory factory = new CommonGridColumnFactory(authorityVo);

        Grid.Column<GroupPermissionDto> actionColumn = factory.addEditRowActionColumn(groupPermissionsGrid, this::openEditLookupWindow);

        actionColumn.setSortable(false).setFlexGrow(0).setWidth("150px").setTextAlign(ColumnTextAlign.CENTER);
        return actionColumn;
    }

    private void openEditLookupWindow(GroupPermissionDto groupPermissionDto) {
        this.lookupWindow = new LookupWindow(groupPermissionDto, groupPermissionsGrid, saveAction);
        lookupWindow.open();
    }

    public Button createAddGroupPermissionsButton() {
        this.addButton = new SysButtonFactory().createSecondaryButton("ADD PERMISSION");
        this.addButton.addClickListener(event -> addGroupPermissionToGrid());
        return this.addButton;
    }

    private void addGroupPermissionToGrid() {
        System.out.println("TODO ADD group permission logic...");
    }

    public void setSaveAction(DefaultConfirmAction<GroupDto, GroupDto> saveAction) {
        this.saveAction = saveAction;
    }
}
