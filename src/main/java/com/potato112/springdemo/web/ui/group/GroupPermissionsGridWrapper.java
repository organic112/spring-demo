package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.SysUINotificationFactory;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.service.user.UserDto;
import com.potato112.springdemo.web.ui.common.SysDropdownFactory;
import com.potato112.springdemo.web.ui.common.SysDropdownMenu;
import com.potato112.springdemo.web.ui.common.SysGridHelper;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.ui.factories.SysGridHeaderFactory;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

public class GroupPermissionsGridWrapper implements Serializable {

    private Grid<GroupPermissionDto> groupPermissionsGrid;
    private BinderWithValueChangeListener<GroupPermissionDto> groupPermissionsBinder;

    private Editor<GroupPermissionDto> editor;

    private Button addButton;
    private Button saveButton;
    private final Collection<Div> editButtons = Collections.newSetFromMap(new WeakHashMap<>());
    private final Collection<Div> deleteButtons = Collections.newSetFromMap(new WeakHashMap<>());

    private boolean gridIsChanged = false;

    /**
     * Wraps grid of current group permission
     */
    public GroupPermissionsGridWrapper(GroupDto bean) {

        if (null == bean) {
            throw new IllegalArgumentException("Group form is not initialized. Bean is null");
        }
        this.groupPermissionsGrid = new Grid<>(GroupPermissionDto.class);
       // this.groupPermissionsGrid = new Grid<>();
        SysGridHelper.initializeGridStyle(groupPermissionsGrid);

        //groupPermissionsGrid.setColumns("viewName","canCreate","canUpdate","canDelete");

        List<GroupPermissionDto> groupPermissions = bean.getGroupPermissions();
        System.out.println("Group permissions size: "+groupPermissions.size());

        this.groupPermissionsGrid.setItems(groupPermissions);


        this.groupPermissionsBinder = new BinderWithValueChangeListener<>(GroupPermissionDto.class);
        this.editor = this.groupPermissionsGrid.getEditor();
        this.editor.setBinder(this.groupPermissionsBinder);
        this.editor.setBuffered(true);

        this.editor.addOpenListener(event -> setupButtons());
        this.editor.addCloseListener(event -> {
           setupButtons();
           this.groupPermissionsGrid.getDataProvider().refreshAll();
        });
        this.groupPermissionsBinder.addValueChangeListener(event ->{
            // boolean notValid =  FIXME
            this.editor.refresh();
        });
    }

    public Grid<GroupPermissionDto> create(){

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
        groupPermissionsGrid.setColumnOrder(viewNameColumn, canCreateColumn,canUpdateColumn ,canDeleteColumn, actionColumn);

        return groupPermissionsGrid;
    }

    private Grid.Column<GroupPermissionDto> configureActionColumn() {



        Grid.Column<GroupPermissionDto> actionColumn = this.groupPermissionsGrid.addComponentColumn(groupPermissionDto -> buildEditDeleteColumnMode( groupPermissionDto));
        buildSaveCancelColumnMode(actionColumn);

        //actionColumn.setSortable(false).setFlexGrow(0).setWidth("150px").setHeader(VaadinIcon.DIAMOND.create()).setTextAlign(ColumnTextAlign.CENTER);
        return actionColumn;
    }

    private void buildSaveCancelColumnMode(Grid.Column<GroupPermissionDto> actionColumn) {

        FormLayout formLayout = new FormLayout();
        SysButtonFactory buttonFactory = new SysButtonFactory();

        Button buttonSave = buttonFactory.createPrimaryButton("CONFIRM");
        buttonSave.addClickListener(event -> confirmNewPermissionAction());

        Button buttonCancel = buttonFactory.createSecondaryButton("CANCEL");
        buttonCancel.addClickListener(event ->{
           GroupPermissionDto item = this.editor.getItem();
           // this.errorTextContainer.setVisible(false); FIXME
           this.editor.cancel();
            if(isGroupPermissionEmpty(item)){
                deleteGroupPermission(item);
            }
        });
        formLayout.add(buttonSave, buttonCancel);
        actionColumn.setEditorComponent(formLayout);
    }

    private void confirmNewPermissionAction() {

        if( groupPermissionsBinder.isValid()){

            // TODO

            editor.save();
        } else {
            // TODO Binder validation

            SysUINotificationFactory.showWarn("VALIDATION ERROR - FIXME");
        }
    }

    private boolean isGroupPermissionEmpty(GroupPermissionDto item) {

        // FIXME
        return true;
    }

    private Component buildEditDeleteColumnMode(GroupPermissionDto groupPermissionDto) {
        if(null != groupPermissionDto.getId()){
            return new Label();
        }

        SysDropdownMenu sysDropdownMenu = new SysDropdownMenu(VaadinIcon.PENCIL.create());

        Div buttonEdit = new SysDropdownFactory().createButtonsFroDropdownMenuItem("EDIT");
        buttonEdit.addClickListener(click -> editGroupPermission(groupPermissionDto));
        editButtons.add(buttonEdit);

        Div buttonDelete = new SysDropdownFactory().createButtonsFroDropdownMenuItem("DELETE");
        buttonEdit.addClickListener(click -> deleteGroupPermission(groupPermissionDto));
        editButtons.add(buttonDelete);

        sysDropdownMenu.addItem(buttonEdit);
        sysDropdownMenu.addItem(buttonDelete);

        return sysDropdownMenu;
    }

    private void deleteGroupPermission(GroupPermissionDto groupPermissionDto) {

        // TODO
    }

    private void editGroupPermission(GroupPermissionDto groupPermissionDto) {
        editor.editItem(groupPermissionDto);
    }


    public Button createAddGroupPermissionsButton(){

        this.addButton = new SysButtonFactory().createSecondaryButton("ADD PERMISSION");
        this.addButton.addClickListener(event -> addGroupPermissionToGrid());
        return this.addButton;
    }

    private void addGroupPermissionToGrid() {
        System.out.println("TODO ADD group permission logic...");

    }

    private void setupButtons() {
        this.addButton.setEnabled(isEditorClose());
        this.editButtons.forEach(button -> button.setEnabled(isEditorClose()));
        this.editButtons.forEach(button -> button.setEnabled(isEditorClose()));
        this.saveButton.setEnabled(isEditorClose());
    }

    private boolean isEditorClose() {
        return true;
        //return !this.editor.isOpen(); FIXME

    }

    public void setSaveButton(Button button ){
        this.saveButton = button;
    }

}
