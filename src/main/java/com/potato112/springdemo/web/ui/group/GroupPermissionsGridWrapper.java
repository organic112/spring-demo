package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.ui.common.SysGridHelper;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;

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

    /**
     * Wraps grid of current group permission
     */
    public GroupPermissionsGridWrapper(GroupDto bean) {

        if (null == bean) {
            throw new IllegalArgumentException("Group form is not initialized. Bean is null");
        }
        this.groupPermissionsGrid = new Grid<>(GroupPermissionDto.class);
        SysGridHelper.initializeGridStyle(groupPermissionsGrid);

        groupPermissionsGrid.setColumns("viewName","canCreate","canUpdate","canDelete");

        List<GroupPermissionDto> groupPermissions = bean.getGroupPermissions();
        System.out.println("Group permissions size: "+groupPermissions.size());

        this.groupPermissionsGrid.setItems(groupPermissions);


        this.groupPermissionsBinder = new BinderWithValueChangeListener<>(GroupPermissionDto.class);
        this.editor = this.groupPermissionsGrid.getEditor();
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

        // TODO add grid logic

        return groupPermissionsGrid;
    }

    public Button createAddGroupPermissionsButton(){

        this.addButton = new SysButtonFactory().createSecondaryButton("ADD");
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
        return !this.editor.isOpen();
    }


}
